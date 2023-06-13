package com.test.service.controller;

import com.minispring.core.Autowired;
import com.minispring.web.RequestMapping;
import com.test.service.BaseService;
import com.test.service.DynamicProxy;
import com.test.service.IAction;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-22 20:06
 */
public class HelloWorldBean {

    @Autowired
    private BaseService baseService;

    @Autowired
    private IAction action;

    @RequestMapping("/helloworld")
    public String doGet() {
        baseService.sayHello();
        return "hello world!";
    }

    @RequestMapping("/testaop")
    public String doTestAop() {
        action.doAction();
        String str = "test aop, hello world aop!";
        return str;
    }

    public String doPost() {
        return "hello world!";
    }
}
