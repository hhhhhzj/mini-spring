package com.minispring.beans.factory.config;

import com.minispring.beans.factory.config.ArgumentValue;

import java.util.*;

/**
 * @author Zhijian.H
 * @since 2023/5/13 下午8:30
 */
public class ArgumentValues {

    private final List<ArgumentValue> genericArgumentValues = new ArrayList<>();

    public List<ArgumentValue> getGenericArgumentValues() {
        return genericArgumentValues;
    }

}
