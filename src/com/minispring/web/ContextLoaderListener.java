package com.minispring.web;

import com.minispring.beans.BeansException;
import lombok.SneakyThrows;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Zhijian.H
 * @since 2023/6/11 上午12:25
 */
public class ContextLoaderListener implements ServletContextListener {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    private WebApplicationContext context;

    public ContextLoaderListener() {
    }
    public ContextLoaderListener(WebApplicationContext context) {
        this.context = context;
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent event) {
        initWebApplicationContext(event.getServletContext());
    }
    private void initWebApplicationContext(ServletContext servletContext) throws BeansException {
        String sContextLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        WebApplicationContext wac = new AnnotationConfigWebApplicationContext(sContextLocation);
        wac.setServletContext(servletContext);
        this.context = wac;
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
    }
}