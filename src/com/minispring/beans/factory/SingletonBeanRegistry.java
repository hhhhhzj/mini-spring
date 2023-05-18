package com.minispring.beans.factory;

/**
 * @author Zhijian.H
 * @since 2023/5/13 下午4:20
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}
