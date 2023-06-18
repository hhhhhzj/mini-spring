package com.test.service;

import com.minispring.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午10:47
 */

public class MyAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("----------my interceptor after method call----------");
    }
}