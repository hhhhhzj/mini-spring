package com.minispring.aop;

import com.minispring.util.PatternMatchUtils;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午11:14
 */
public class NameMatchMethodPointcutAdvisor implements PointcutAdvisor {

    private Advice advice;

    private String mappedName;

    private MethodInterceptor methodInterceptor;

    public void setAdvice(Advice advice) {

        MethodInterceptor mi = null;
        if (advice instanceof MethodBeforeAdvice) {
            mi = new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advice);
        } else if (advice instanceof AfterReturningAdvice) {
            mi = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        } else {
            mi = (MethodInterceptor) advice;
        }
        this.setMethodInterceptor(mi);
    }

    public boolean isMatcher(Method method) {
        if (mappedName.equals(method.getName()) || isMatch(method.getName(), mappedName)) {
            return true;
        }
        return false;
    }

    //核心方法，判断方法名是否匹配给定的模式
    protected boolean isMatch(String methodName, String mappedName) {
        return PatternMatchUtils.simpleMatch(mappedName, methodName);
    }

    public String getMappedName() {
        return mappedName;
    }

    public void setMappedName(String mappedName) {
        this.mappedName = mappedName;
    }

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
