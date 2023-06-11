package com.minispring.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午6:36
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    public RequestMappingHandlerAdapter() {
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        handleInternal(request, response, (HandlerMethod) handler);
    }
    private void handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        Method method = handler.getMethod();
        Object obj = handler.getBean();
        Object objResult = null;
        try {
            objResult = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().append(objResult.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}