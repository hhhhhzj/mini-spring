package com.minispring.beans.factory.config;


import com.minispring.beans.BeansException;
import com.minispring.beans.factory.BeanFactory;

public interface BeanFactoryPostProcessor {
	void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
