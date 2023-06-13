package com.test.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Zhijian.H
 * @since 2023/6/13 下午10:06
 */
public class DynamicProxy {
    private Object subject = null;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(DynamicProxy.class
                        .getClassLoader(), subject.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("doAction")) {
                            System.out.println("before call real object........");
                            return method.invoke(subject, args);
                        }
                        return null;
                    }
                });
    }
}
