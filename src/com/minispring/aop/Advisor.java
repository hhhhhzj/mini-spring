package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午5:22
 */
public interface Advisor {
    MethodInterceptor getMethodInterceptor();
    void setMethodInterceptor(MethodInterceptor methodInterceptor);
}
