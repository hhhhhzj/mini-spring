package com.minispring.beans.factory;

import com.minispring.core.BeanPostProcessor;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-06-01 19:35
 */
public interface ConfigurableBeanFactory extends
        BeanFactory {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void registerDependentBean(String beanName, String dependentBeanName);
    String[] getDependentBeans(String beanName);
    String[] getDependenciesForBean(String beanName);
}
