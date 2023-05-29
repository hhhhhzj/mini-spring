package com.test.service;

import com.minispring.beans.BeansException;
import com.minispring.context.ClassPathXmlApplicationContext;
import com.test.service.entity.User;

import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-11 11:45
 */
public class Test1 {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml", false);
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
        ((AServiceImpl)aService).baseSayHello();

//        {
//            UserService userService = (UserService) ctx.getBean("userService");
//            List<User> userList = userService.getUserInfo(1);
//            System.out.println(userList.toString());
//        }
//
//        {
//            UserServiceV2 userService = (UserServiceV2) ctx.getBean("userServiceV2");
//            User user = userService.getUserInfo(1);
//            System.out.println(user.toString());
//        }
    }
}

