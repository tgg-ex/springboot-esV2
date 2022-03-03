package com.tt.es.utils;

import org.springframework.stereotype.Component;

@Component
public class IDGenerator {

    public long getGlobalId() {
        final IdWorker idWorker = IdWorker.getInstance();
        return idWorker.nextId();
    }
}
