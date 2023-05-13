package com.minispring.beans.factory.impl;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.BeanFactory;
import com.minispring.beans.factory.config.BeanDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-12 20:58
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {}


    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = singletons.get(beanName);
        if (singleton != null) {
            return singleton;
        }
        int beanIndex = beanNames.indexOf(beanName);
        if (beanIndex == -1) {
            throw new BeansException("不存在beanDefinition");
        }
        BeanDefinition beanDefinition = beanDefinitions.get(beanIndex);
        try {
            singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            singletons.put(beanName, singleton);
        } catch (Exception e) {

        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}


