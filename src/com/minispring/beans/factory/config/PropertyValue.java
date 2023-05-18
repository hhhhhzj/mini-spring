package com.minispring.beans.factory.config;

/**
 * @author Zhijian.H
 * @since 2023/5/13 下午8:28
 */

public class PropertyValue {
    private String type;
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(String type ,String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
