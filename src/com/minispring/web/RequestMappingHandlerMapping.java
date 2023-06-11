package com.minispring.web;

import com.minispring.beans.BeansException;
import com.minispring.core.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhijian.H
 * @since 2023/6/11 下午6:16
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

    private List<String> packageNames = new ArrayList<>();
    private WebApplicationContext parentApplicationContext;

    private List<String> controllerNames;
    private Map<String,Object> controllerObjs = new HashMap<>();
    private Map<String,Class<?>> controllerClasses = new HashMap<>();

    private MappingRegistry mappingRegistry;


    RequestMappingHandlerMapping( List<String> packageNames, WebApplicationContext parentApplicationContext) {
        this.packageNames = packageNames;
        this.parentApplicationContext = parentApplicationContext;
        mappingRegistry = new MappingRegistry();
        init();
    }
    @lombok.SneakyThrows
    public void init() {
        initController();
        initMapping();
    }

    @lombok.SneakyThrows
    @Override
    public HandlerMethod getHandler(HttpServletRequest request) {
        String sPath = request.getServletPath(); //获取请求的path
        Method method = this.mappingRegistry.getMappingMethods().get(sPath); //获取调用方法名
        if (method == null) {
            return null;
        }
        Object obj = this.mappingRegistry.getMappingObjs().get(sPath);  //获取bean实例

        HandlerMethod handlerMethod = new HandlerMethod(method, obj);
        return handlerMethod;
    }

    protected void initController() throws BeansException {
        //扫描包，获取所有类名
        this.controllerNames = scanPackages(this.packageNames);
        for (String controllerName : this.controllerNames) {
            Object obj = null;
            Class<?> clz = null;
            try {
                clz = Class.forName(controllerName); //加载类
                this.controllerClasses.put(controllerName, clz);
            } catch (Exception e) {
            }
            try {
                obj = clz.newInstance(); //实例化bean
                this.controllerObjs.put(controllerName, obj);
            } catch (Exception e) {
            }
            populateBean(obj);
        }
    }

    private List<String> scanPackages(List<String> packages) {
        List<String> tempControllerNames = new ArrayList<>();
        for (String packageName : packages) {
            tempControllerNames.addAll(scanPackage(packageName));
        }
        return tempControllerNames;
    }

    private List<String> scanPackage(String packageName) {
        List<String> tempControllerNames = new ArrayList<>();
        URI uri = null;
        //将以.分隔的包名换成以/分隔的uri
        try {
            uri = this.getClass().getResource("/" +
                    packageName.replaceAll("\\.", "/")).toURI();
        } catch (Exception e) {
        }
        File dir = new File(uri);
        //处理对应的文件目录
        for (File file : dir.listFiles()) { //目录下的文件或者子目录
            if(file.isDirectory()){ //对子目录递归扫描
                scanPackage(packageName+"."+file.getName());
            }else{ //类文件
                String controllerName = packageName +"."
                        +file.getName().replace(".class", "");
                tempControllerNames.add(controllerName);
            }
        }
        return tempControllerNames;
    }

    /**
     * controller里面字段注入
     *
     * @param bean
     */
    private void populateBean(Object bean) throws BeansException {
        Class<?> clz = bean.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (isAutowired) {
                String fieldName = field.getName();
                Object autowiredBean = this.parentApplicationContext.getBean(fieldName);
                field.setAccessible(true);
                try {
                    field.set(bean, autowiredBean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected void initMapping() {
        for (String controllerName : this.controllerNames) {
            Class<?> clazz = this.controllerClasses.get(controllerName);
            Object obj = this.controllerObjs.get(controllerName);
            Method[] methods = clazz.getDeclaredMethods();
            if (methods != null) {
                for (Method method : methods) {
                    //检查所有的方法
                    boolean isRequestMapping =
                            method.isAnnotationPresent(RequestMapping.class);
                    if (isRequestMapping) { //有RequestMapping注解
                        String methodName = method.getName();
                        //建立方法名和URL的映射
                        String urlMapping = method.getAnnotation(RequestMapping.class).value();
                        this.mappingRegistry.getUrlMappingNames().add(urlMapping);
                        this.mappingRegistry.getMappingObjs().put(urlMapping, obj);
                        this.mappingRegistry.getMappingMethods().put(urlMapping, method);
                    }
                }
            }
        }
    }

}
