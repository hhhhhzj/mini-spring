package com.test.service;

import com.minispring.beans.BeansException;
import com.minispring.context.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-11 11:45
 */
public class Test1 {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
}

