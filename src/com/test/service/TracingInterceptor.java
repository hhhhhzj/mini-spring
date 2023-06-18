package com.test.service;

import com.minispring.aop.MethodInterceptor;
import com.minispring.aop.MethodInvocation;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午6:05
 */
public class TracingInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation i) throws Throwable {
        System.out.println("method " + i.getMethod() + " is called on " +
                i.getThis() + " with args " + i.getArguments());
        Object ret = i.proceed();
        System.out.println("method " + i.getMethod() + " returns " + ret);
        return ret;
    }
}