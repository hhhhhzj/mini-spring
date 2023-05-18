package com.minispring.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhijian.H
 * @since 2023/5/13 下午8:31
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }
}
