package com.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午5:59
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    private Method method;

    private Object[] args;

    private Object target;

    ReflectiveMethodInvocation(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }
}
