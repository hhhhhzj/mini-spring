package com.minispring.beans.factory;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午6:56
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory);
}
