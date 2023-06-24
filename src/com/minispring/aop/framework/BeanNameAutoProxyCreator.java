package com.minispring.aop.framework;

import com.minispring.aop.ProxyFactoryBean;
import com.minispring.beans.BeansException;
import com.minispring.beans.factory.BeanFactory;
import com.minispring.core.BeanPostProcessor;
import com.minispring.util.PatternMatchUtils;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-06-20 11:54
 */
public class BeanNameAutoProxyCreator implements BeanPostProcessor {

    private BeanFactory beanFactory;

    private String interceptorName;
    private String pattern;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (isMatch(beanName, pattern)) {
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setBeanFactory(beanFactory);
            proxyFactoryBean.setTarget(bean);
            proxyFactoryBean.setInterceptorName(interceptorName);

            bean = proxyFactoryBean;
            return proxyFactoryBean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }

    protected boolean isMatch(String beanName, String mappedName) {
        System.out.println(" match?" + beanName + ":" +mappedName);
        return PatternMatchUtils.simpleMatch(mappedName, beanName);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
