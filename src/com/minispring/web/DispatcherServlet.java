package com.minispring.web;

import com.minispring.beans.BeansException;
import com.minispring.core.Autowired;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-22 16:38
 */
public class DispatcherServlet extends HttpServlet {

    private String sContextConfigLocation;

    private List<String> packageNames = new ArrayList<>();

    private List<String> controllerNames = new ArrayList<>();
    private Map<String,Object> controllerObjs = new HashMap<>();
    private Map<String,Class<?>> controllerClasses = new HashMap<>();

    private List<String> urlMappingNames = new ArrayList<>();

    private Map<String, MappingValue> mappingValues;

    //Map<url, controller.class>
    private Map<String, Class<?>> mappingClz = new HashMap<>();
    //Map<url, controller>>
    private Map<String, Object> mappingObjs = new HashMap<>();
    //Map<url, method>>
    private Map<String,Method> mappingMethods = new HashMap<>();

    private WebApplicationContext parentApplicationContext;

    private WebApplicationContext webApplicationContext;

    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    @lombok.SneakyThrows
    @Override
    public void init(ServletConfig config) {
        super.init(config);
        this.parentApplicationContext = (WebApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        sContextConfigLocation = config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;
        try {
            xmlPath = this.getServletContext().getResource(sContextConfigLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);

        refresh();
    }

    @Override
    public ServletConfig getServletConfig() {
        return super.getServletConfig();
    }

    protected void refresh() throws BeansException {
        this.requestMappingHandlerMapping = new RequestMappingHandlerMapping(packageNames, parentApplicationContext);
    }



    //对所有的mappingValues中注册的类进行实例化，默认构造函数
    protected void Refresh() {
        for (Map.Entry<String,MappingValue> entry : mappingValues.entrySet()) {
            String id = entry.getKey();
            String className = entry.getValue().getClz();
            Object obj = null;
            Class<?> clz = null;
            try {
                clz = Class.forName(className);
                obj = clz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mappingClz.put(id, clz);
            mappingObjs.put(id, obj);
        }
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
        super.service(servletRequest, servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        HandlerMethod handlerMethod = requestMappingHandlerMapping.getHandler(request);
        if (handlerMethod == null) {
            response.getWriter().append("no url map");
            return;
        }
        HandlerAdapter ha = new RequestMappingHandlerAdapter();
        ha.handle(request, response, handlerMethod);
    }
}
