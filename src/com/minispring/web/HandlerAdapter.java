package com.minispring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午5:10
 */
public interface HandlerAdapter {
    void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}

