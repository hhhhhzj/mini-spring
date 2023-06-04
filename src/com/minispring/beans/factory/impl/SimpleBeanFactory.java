package com.minispring.beans.factory.impl;

import com.minispring.beans.factory.BeanFactory;
import com.minispring.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-12 20:58
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    public SimpleBeanFactory() {}

    @Override
    public Object getBean(String beanName) {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        try {
            Class<?> clz = Class.forName(beanDefinition.getClassName());

            ArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
            //使用构造函数new对象
            if (constructorArgumentValues != null && constructorArgumentValues.getGenericArgumentValues().size() > 0) {
                List<ArgumentValue> genericArgumentValues = constructorArgumentValues.getGenericArgumentValues();

                int argumentSize = genericArgumentValues.size();
                Class<?>[] paramTypes = new Class<?>[argumentSize];
                Object[] paramValues = new Object[argumentSize];

                for (int i = 0; i < argumentSize; i++) {
                    ArgumentValue argumentValue = genericArgumentValues.get(i);
                    switch (argumentValue.getType()) {
                        case "String":
                        case "java.lang.String":
                            paramTypes[i] = String.class;
                            paramValues[i] = argumentValue.getValue();
                            break;
                        case "Integer":
                        case "java.lang.Integer":
                            paramTypes[i] = Integer.class;
                            paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                            break;
                        case "int":
                            paramTypes[i] = int.class;
                            paramValues[i] = Integer.parseInt((String) argumentValue.getValue());
                        default:
                           break;
                    }
                }

                Constructor<?> con = clz.getConstructor(paramTypes);
                singleton = con.newInstance(paramValues);

            } else {
                singleton = clz.newInstance();
            }

            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (propertyValues != null && propertyValues.getPropertyValueList() != null) {
                for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                    String pType = propertyValue.getType();
                    String ref = propertyValue.getRef();
                    String pName = propertyValue.getName();
                    String pValue = (String) propertyValue.getValue();

                    String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                    Class<?>[] paramTypes = new Class<?>[1];
                    Object[] paramValues = new Object[1];

                    switch (pType) {
                        case "String":
                        case "java.lang.String":
                            paramTypes[0] = String.class;
                            paramValues[0] = pValue;
                            break;
                        case "Integer":
                        case "java.lang.Integer":
                            paramTypes[0] = Integer.class;
                            paramValues[0] = Integer.valueOf(pValue);
                            break;
                        case "int":
                            paramTypes[0] = int.class;
                            paramValues[0] = Integer.parseInt(pValue);
                        default:   //对象类型
                            if (ref != null) {
                                paramValues[0] = getBean(ref);
                                paramTypes[0] = paramValues[0].getClass();
                            }
                            break;
                    }

                    Method method = null;
                    try {
                        method = clz.getMethod(methodName, paramTypes);
                        method.invoke(singleton, paramValues);
                    } catch(Exception ignored)  {

                    }
                }
            }

            registerBean(beanName, singleton);
        } catch (Exception e) {

        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(beanDefinition.getId());
            } catch (Exception ignored) {

            }
        }

    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public void refresh() {

    }
}


