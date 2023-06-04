package com.minispring.core;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.BeanFactory;

/**
 * @author Zhijian.H
 * @since 2023/5/28 下午6:16
 */
public interface BeanPostProcessor {

    /**
     * bean初始化之前调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * bean初始化之后
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);
}
