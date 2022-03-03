package com.tt.es.service;

import com.alibaba.fastjson.JSONObject;
import com.tt.es.document.EsDemoDocument;

import java.util.List;

public interface IEsDemoTemplateService {

    void save(EsDemoDocument esDemoDocument);

    List<EsDemoDocument> findByName(String name);

    void del(String id);

    void bulkIndex();

    JSONObject list(String name);
}
