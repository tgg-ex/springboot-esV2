package com.tt.es.repository;

import com.tt.es.document.EsDemoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zeng
 * <p>
 * 使用ElasticsearchRepository crud
 **/
@Component
public interface EsDemoRepository extends ElasticsearchRepository<EsDemoDocument, String> {

    EsDemoDocument findByName(String name);

    void deleteById(String id);

}
