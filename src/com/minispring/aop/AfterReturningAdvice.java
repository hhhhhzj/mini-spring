package com.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午10:08
 */
public interface AfterReturningAdvice extends AfterAdvice {
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}