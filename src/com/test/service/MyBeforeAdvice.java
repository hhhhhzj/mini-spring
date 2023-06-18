package com.test.service;

import com.minispring.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午10:48
 */

public class MyBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("----------my interceptor before method call----------");
    }
}
