package com.minispring.beans.factory;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.config.BeanDefinition;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-11 11:55
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    void registerBeanDefinition(BeanDefinition beanDefinition);
    void registerBean(String beanName, Object obj);
}