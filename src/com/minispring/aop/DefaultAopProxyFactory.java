package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午12:23
 */
public class DefaultAopProxyFactory implements AopProxyFactory{

    @Override
    public AopProxy createAopProxy(Object target, PointcutAdvisor advisor) {
        JdkDynamicAopProxy proxy = new JdkDynamicAopProxy();
        proxy.setTarget(target);
        proxy.setAdvisor(advisor);
        return proxy;
    }
}
