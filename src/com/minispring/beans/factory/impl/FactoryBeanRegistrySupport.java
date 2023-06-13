package com.minispring.beans.factory.impl;

import com.minispring.beans.factory.FactoryBean;

/**
 * @author Zhijian.H
 * @since 2023/6/13 下午10:19
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    protected Object getObjectFromFactoryBean(FactoryBean<?> factory, String beanName) {
        Object object = doGetObjectFromFactoryBean(factory, beanName);
        return object;
    }

    //从factory bean中获取内部包含的对象
    private Object doGetObjectFromFactoryBean(final FactoryBean<?> factory, final String beanName) {
        Object object = null;
        try {
            object = factory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}