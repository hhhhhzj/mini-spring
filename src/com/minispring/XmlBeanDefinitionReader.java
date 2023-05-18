package com.minispring;

import com.minispring.beans.factory.config.ArgumentValue;
import com.minispring.beans.factory.config.BeanDefinition;
import com.minispring.beans.factory.config.PropertyValue;
import com.minispring.core.Resource;
import org.dom4j.Element;
import com.minispring.beans.factory.BeanFactory;

import java.util.List;

/**
 * xml资源解析成beanDefinition
 *
 * @author zhijian05.huang
 * @date 2023-05-11 12:22
 */
public class XmlBeanDefinitionReader {
    private BeanFactory beanFactory;
    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);

            //处理属性
            List<Element> propertyElements = element.elements("property");
            if (propertyElements != null) {
                for (Element propertyElement : propertyElements) {
                    String pType = propertyElement.attributeValue("type");
                    String pName = propertyElement.attributeValue("name");
                    String pValue = propertyElement.attributeValue("value");
                    beanDefinition.addPropertyValue(new PropertyValue(pType, pName, pValue));
                }
            }

            //处理构造器参数
            List<Element> constructorElements = element.elements("constructor-arg");
            if (constructorElements != null) {
                for (Element e : constructorElements) {
                    String aType = e.attributeValue("type");
                    String aName = e.attributeValue("name");
                    String aValue = e.attributeValue("value");
                    beanDefinition.addArgumentValue(new ArgumentValue(aType, aName, aValue));
                }
            }

            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
