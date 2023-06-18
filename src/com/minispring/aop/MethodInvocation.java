package com.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午5:20
 */
public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object getThis();
    Object proceed() throws Throwable;
}
