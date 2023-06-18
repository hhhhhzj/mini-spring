package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午5:52
 */
public class DefaultAdvisor implements Advisor {

    private MethodInterceptor methodInterceptor;

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
