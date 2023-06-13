package com.minispring.beans.factory;

/**
 * @author Zhijian.H
 * @since 2023/6/13 下午10:17
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    default boolean isSingleton() {
        return true;
    }
}
