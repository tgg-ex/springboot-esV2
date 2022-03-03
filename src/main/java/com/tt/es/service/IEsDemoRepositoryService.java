package com.tt.es.service;

import com.tt.es.document.EsDemoDocument;

public interface IEsDemoRepositoryService {

    EsDemoDocument findByName(String name);

    void save(EsDemoDocument demoDocument);

    void del(String id);
}
