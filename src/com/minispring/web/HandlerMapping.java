package com.minispring.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午5:09
 */
public interface HandlerMapping {
    HandlerMethod getHandler(HttpServletRequest request) throws Exception;
}
