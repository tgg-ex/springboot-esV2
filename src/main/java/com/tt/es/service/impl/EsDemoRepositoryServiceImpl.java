package com.tt.es.service.impl;

import com.tt.es.document.EsDemoDocument;
import com.tt.es.repository.EsDemoRepository;
import com.tt.es.service.IEsDemoRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zeng
 *
 * 使用ElasticsearchRepository crud
 */

@Service
public class EsDemoRepositoryServiceImpl implements IEsDemoRepositoryService {


    @Autowired
    private EsDemoRepository esDemoRepository;

    @Override
    public EsDemoDocument findByName(String name) {
        return esDemoRepository.findByName(name);
    }

    @Override
    public void save(EsDemoDocument demoDocument) {
        esDemoRepository.save(demoDocument);
    }

    @Override
    public void del(String id) {
        esDemoRepository.deleteById(id);
    }
}
