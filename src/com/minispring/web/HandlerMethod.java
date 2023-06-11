package com.minispring.web;

import java.lang.reflect.Method;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午6:05
 */
public class HandlerMethod {
    private  Object bean;
    private  Class<?> beanType;
    private  Method method;
    //private  MethodParameter[] parameters;
    private  Class<?> returnType;
    private  String description;
    private  String className;
    private  String methodName;

    public HandlerMethod(Method method, Object obj) {
        this.setMethod(method);
        this.setBean(obj);
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public Object getBean() {
        return bean;
    }
    public void setBean(Object bean) {
        this.bean = bean;
    }
}
