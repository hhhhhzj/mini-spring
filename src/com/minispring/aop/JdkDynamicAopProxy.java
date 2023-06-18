package com.minispring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午12:21
 */
public class JdkDynamicAopProxy implements  AopProxy, InvocationHandler {

    private Object target;

    private PointcutAdvisor advisor;

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(ProxyFactoryBean.class
                        .getClassLoader(), target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (advisor != null) {
            if (advisor.isMatcher(method)) {
                MethodInterceptor methodInterceptor = advisor.getMethodInterceptor();
                ReflectiveMethodInvocation methodInvocation = new ReflectiveMethodInvocation(method, target, args);
                return methodInterceptor.invoke(methodInvocation);
            }
        }

        return method.invoke(target, args);
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(PointcutAdvisor advisor) {
        this.advisor = advisor;
    }
}
