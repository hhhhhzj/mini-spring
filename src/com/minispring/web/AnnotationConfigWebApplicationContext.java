package com.minispring.web;

import com.minispring.beans.BeansException;
import com.minispring.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
/**
 * @author Zhijian.H
 * @since 2023/6/11 上午12:31
 */
public class AnnotationConfigWebApplicationContext extends ClassPathXmlApplicationContext implements WebApplicationContext{

    private ServletContext servletContext;

    public AnnotationConfigWebApplicationContext(String fileName) throws BeansException {
        super(fileName);
    }

    @lombok.SneakyThrows
    public AnnotationConfigWebApplicationContext(String fileName, WebApplicationContext parentApplicationContext) throws BeansException {
        super(fileName);
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}