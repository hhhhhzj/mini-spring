package com.minispring.aop;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午11:11
 */
public interface PointcutAdvisor extends Advisor {
    public boolean isMatcher(Method method);
}
