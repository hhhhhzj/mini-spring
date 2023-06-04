package com.minispring.beans.factory;

import com.minispring.beans.BeansException;
import com.minispring.core.BeanPostProcessor;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-06-01 19:46
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    int getBeanPostProcessorCount();

}
