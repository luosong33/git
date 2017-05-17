package com.jumore.jmbi.model.param;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 基础属性值
 * @author zhuwei
 *
 */
public class BaseParam implements Serializable {

    /**
     * 序列号
     */
    private static final long               serialVersionUID = 8531892087905296967L;

    private LinkedHashMap<String, SortType> sortMap;

    public enum SortType {
        ASC, DESC
    }

    public LinkedHashMap<String, SortType> getSortMap() {
        return sortMap;
    }

    public void setSortMap(LinkedHashMap<String, SortType> sortMap) {
        this.sortMap = sortMap;
    }

    public void addSort(String order, SortType sort) {
        if (sortMap == null) {
            sortMap = new LinkedHashMap<String, SortType>();
        }
        sortMap.put(order, sort);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
