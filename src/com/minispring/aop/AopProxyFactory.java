package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午12:19
 */
public interface AopProxyFactory {
    AopProxy createAopProxy(Object target, PointcutAdvisor advisor);
}
