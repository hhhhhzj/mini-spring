package com.minispring.beans.factory.config;

import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-11 11:27
 */

public class BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    private String id;

    private String className;

    private ArgumentValues constructorArgumentValues;

    private PropertyValues propertyValues;

    private String scope = SCOPE_SINGLETON;

    private String initMethodName;

    private boolean lazyInit = true;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        if (propertyValues == null) {
            propertyValues = new PropertyValues();
        }
        List<PropertyValue> propertyValueList = propertyValues.getPropertyValueList();
        propertyValueList.add(propertyValue);
    }

    public void addArgumentValue(ArgumentValue argumentValue) {
        if (constructorArgumentValues == null) {
            constructorArgumentValues = new ArgumentValues();
        }
        constructorArgumentValues.getGenericArgumentValues().add(argumentValue);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArgumentValues getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    public void setConstructorArgumentValues(ArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }
}
