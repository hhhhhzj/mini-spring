package com.minispring;

import com.minispring.beans.factory.config.BeanDefinition;
import com.minispring.core.Resource;
import org.dom4j.Element;
import com.minispring.beans.factory.BeanFactory;

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
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
