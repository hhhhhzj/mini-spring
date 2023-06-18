package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午5:15
 */
public interface MethodInterceptor extends Interceptor {
    Object invoke(MethodInvocation invocation) throws Throwable;
}
