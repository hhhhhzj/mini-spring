package com.minispring.beans.factory;

import com.minispring.beans.BeansException;
import java.util.Map;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-06-01 19:29
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanNamesForType(Class<?> type);
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
