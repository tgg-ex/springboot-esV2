package com.tt.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tt.es.document.EsDemoDocument;
import com.tt.es.service.IEsDemoTemplateService;
import com.tt.es.utils.IDGenerator;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * @author zeng
 * <p>
 * 使用 ElasticsearchTemplate crud, 分词查询，分页
 */
@Service
public class EsDemoTemplateServiceImpl implements IEsDemoTemplateService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    private IDGenerator idGenerator;

    @Override
    public void save(EsDemoDocument esDemoDocument) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(esDemoDocument.getId()).withObject(esDemoDocument).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Override
    public List<EsDemoDocument> findByName(String name) {
        //分页
        Pageable pageable = new PageRequest(0, 3);
        //模糊匹配
//        SearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("name", name)).withPageable(pageable).build();
        //Term全等查询
//        SearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termsQuery("name", name)).withPageable(pageable).build();
        //其余查询
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name", name)).withPageable(pageable).build();

//        SearchQuery query = new NativeSearchQueryBuilder().withPageable(pageable).build();

        List<EsDemoDocument> esDemoDocuments = elasticsearchTemplate.queryForList(query, EsDemoDocument.class);
        return esDemoDocuments;
    }

    @Override
    public void del(String id) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(QueryBuilders.matchQuery("id", id));
//        deleteQuery.setQuery(QueryBuilders.rangeQuery());
        elasticsearchTemplate.delete(deleteQuery);
    }

    @Override
    public void bulkIndex() {
        List<IndexQuery> queries = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            EsDemoDocument document = new EsDemoDocument();
            long globalId = idGenerator.getGlobalId();
            document.setId(String.valueOf(globalId));
            document.setAge(String.valueOf(globalId));
            document.setName(String.valueOf(globalId));
            document.setSex(String.valueOf(globalId));
            IndexQuery indexQuery = new IndexQueryBuilder().withId(document.getId()).withObject(document).build();

            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
        elasticsearchTemplate.refresh("es-demo");

    }

    @Override
    public JSONObject list(String name) {
        JSONObject param = new JSONObject();

        //分词处理
        QueryBuilder matchQuery = QueryBuilders.matchQuery("name", name).analyzer("ik_max_word")
                .operator(Operator.OR);   // matchQuery(),单字段搜索
        //构建查询
        SearchResponse searchResponse = elasticsearchTemplate.getClient().prepareSearch("es-demo").setQuery(matchQuery).setFrom(0).setSize(2).get();
        String searchResponseString = JSONObject.toJSONString(searchResponse);
        System.out.println(" searchResponseString：" + searchResponseString);
        //查询结果
        SearchHits hits = searchResponse.getHits();
        List<EsDemoDocument> esDemoDocumentList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            EsDemoDocument document = JSONObject.parseObject(sourceAsString, EsDemoDocument.class);
            esDemoDocumentList.add(document);
        }
        long totalHits = hits.getTotalHits();
        param.put("rows", esDemoDocumentList);
        param.put("total", totalHits);
        return param;
    }
}
