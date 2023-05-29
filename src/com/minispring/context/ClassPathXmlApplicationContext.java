package com.minispring.context;

import com.minispring.XmlBeanDefinitionReader;
import com.minispring.beans.BeansException;
import com.minispring.beans.factory.config.BeanDefinition;

import com.minispring.beans.factory.BeanFactory;
import com.minispring.beans.factory.impl.AutowireCapableBeanFactory;
import com.minispring.core.AutowiredAnnotationBeanPostProcessor;
import com.minispring.core.ClassPathXmlResource;
import com.minispring.core.Resource;


/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-11 11:31
 */
public class ClassPathXmlApplicationContext {

    private final BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) throws BeansException {
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isfresh) throws BeansException {
        beanFactory = new AutowireCapableBeanFactory();
        Resource classPathXmlResource = new ClassPathXmlResource(fileName);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathXmlResource);

        registerBeanPostProcessors(this.beanFactory);

        if (isfresh) {
            refresh();
        }
    }

    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }

    public void refresh() throws BeansException, IllegalStateException {        // Register bean processors that intercept bean creation.
        // Initialize other special beans in specific context subclasses.
        onRefresh();
    }

    private void registerBeanPostProcessors(BeanFactory beanFactory) {
        ((AutowireCapableBeanFactory) beanFactory).addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    private void onRefresh() {
        this.beanFactory.refresh();
    }
}
