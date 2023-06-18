package com.minispring.aop;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.BeanFactory;
import com.minispring.beans.factory.BeanFactoryAware;
import com.minispring.beans.factory.FactoryBean;

/**
 * @author Zhijian.H
 * @since 2023/6/13 下午10:25
 */
public class ProxyFactoryBean implements FactoryBean<Object>, BeanFactoryAware {

    private BeanFactory beanFactory;

    private Object target;

    private Object singletonInstance;

    private AopProxyFactory aopProxyFactory;

    private String interceptorName;

    private PointcutAdvisor advisor;

    public ProxyFactoryBean() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private synchronized void initializeAdvisor() {

        if (interceptorName == null) {
            return;
        }

        MethodInterceptor mi = null;
        try {
            advisor = (PointcutAdvisor) this.beanFactory.getBean(this.interceptorName);
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object getObject() throws Exception {
        if (singletonInstance == null) {
            initializeAdvisor();
            singletonInstance = aopProxyFactory.createAopProxy(target, advisor).getProxy();
        }
        return singletonInstance;
    }

    @Override
    public Class<?> getObjectType() {
        return target.getClass();
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getInterceptorName() {
        return interceptorName;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }
}
