package com.minispring.beans.factory.impl;

import com.minispring.beans.BeansException;
import com.minispring.beans.factory.*;
import com.minispring.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-12 20:58
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    protected Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    protected List<String> beanDefinitionNames = new ArrayList<>();
    protected Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(256);

    public abstract Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;
    public abstract Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;

    public AbstractBeanFactory() {}

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton == null) {
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                singleton = createBean(beanName);
                registerBean(beanName, singleton);
                if (singleton instanceof BeanFactoryAware) {
                    ((BeanFactoryAware) singleton).setBeanFactory(this);
                }

                // 预留beanpostprocessor位置
                singleton = applyBeanPostProcessorBeforeInitialization(singleton, beanName);
                // step 2: afterPropertiesSet
                // step 3: init-method
                applyBeanPostProcessorAfterInitialization(singleton, beanName);
                this.removeSingleton(beanName);
                this.registerBean(beanName, singleton);
                System.out.println(singleton);
            }
        }

        //处理factorybean
        if (singleton instanceof FactoryBean) {
            return this.getObjectForBeanInstance(singleton, beanName);
        }

        return singleton;
    }


    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // Now we have the bean instance, which may be a normal bean or a FactoryBean.
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = null;
        FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
        object = getObjectFromFactoryBean(factory, beanName);
        return object;
    }


    @Override
    public void refresh() {
        for (String beanName : beanDefinitions.keySet()) {
            try {
                getBean(beanName);
            } catch (Exception ignored) {
            }
        }
    }


    private Object createBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        Object singleton = doCreateBean(beanDefinition);
        this.earlySingletonObjects.put(beanName, singleton);
        handleProperties(beanDefinition, singleton);
        return singleton;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) {
        Object singleton = null;
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
        } catch (Exception e) {

        }
        return singleton;

    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionNames.add(beanDefinition.getId());
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
    public void registerDependentBean(String beanName, String dependentBeanName) {
        super.registerDependentBean(beanName, dependentBeanName);
    }

    @Override
    public boolean hasDependentBean(String beanName) {
        return super.hasDependentBean(beanName);
    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return super.getDependentBeans(beanName);
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return super.getDependenciesForBean(beanName);
    }

    private void handleProperties(BeanDefinition beanDefinition, Object singleton) {
        try {
            Class<?> clz = Class.forName(beanDefinition.getClassName());

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
                                paramTypes[0] = Class.forName(pType);
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


        } catch (Exception e) {

        }
    }
}


