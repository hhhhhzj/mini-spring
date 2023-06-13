package com.minispring.aop;

import com.minispring.beans.factory.FactoryBean;
import com.test.service.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Zhijian.H
 * @since 2023/6/13 下午10:25
 */
public class ProxyFactoryBean implements FactoryBean<Object> {

    private Object target;

    private Object singletonInstance;


    @Override
    public Object getObject() throws Exception {
        if (singletonInstance == null) {
            singletonInstance = Proxy.newProxyInstance(ProxyFactoryBean.class
                            .getClassLoader(), target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("before call real object........");
                            return method.invoke(target, args);
                        }
                    });
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
}
