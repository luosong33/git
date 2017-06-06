package com.jumore.dove.demo.common.model;

import org.springframework.beans.factory.annotation.Value;

public class Configuration {

    @Value(value="${sys.dataPath}")
    private String dataPath;
    
    public String getDataPath() {
        return dataPath;
    }
}
