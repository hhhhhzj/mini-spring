package com.minispring.aop;

/**
 * @author Zhijian.H
 * @since 2023/6/18 下午11:12
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();
}
