package com.minispring.web;

import com.minispring.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * @author Zhijian.H
 * @since 2023/6/11 上午12:26
 */

public interface WebApplicationContext extends ApplicationContext {

    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";

    ServletContext getServletContext();
    void setServletContext(ServletContext servletContext);
}
