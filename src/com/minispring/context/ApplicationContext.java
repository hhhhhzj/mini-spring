package com.minispring.context;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.ConfigurableBeanFactory;
import com.minispring.beans.factory.ConfigurableListableBeanFactory;
import com.minispring.beans.factory.ListableBeanFactory;
import com.minispring.beans.factory.config.BeanFactoryPostProcessor;
import com.minispring.core.ApplicationEventPublisher;
import com.minispring.core.env.Environment;
import com.minispring.core.env.EnvironmentCapable;

/**
 * @author Zhijian.H
 * @since 2023/6/4 下午8:15
 */
public interface ApplicationContext
        extends EnvironmentCapable, ListableBeanFactory,
        ConfigurableBeanFactory, ApplicationEventPublisher {
    String getApplicationName();
    long getStartupDate();
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
    void setEnvironment(Environment environment);
    Environment getEnvironment();
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
    void refresh() throws BeansException, IllegalStateException;
    void close();
    boolean isActive();
}
