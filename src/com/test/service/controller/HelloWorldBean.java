package com.test.service.controller;

import com.minispring.core.Autowired;
import com.minispring.web.RequestMapping;
import com.test.service.BaseService;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-22 20:06
 */
public class HelloWorldBean {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/helloworld")
    public String doGet() {
        baseService.sayHello();
        return "hello world!";
    }

    public String doPost() {
        return "hello world!";
    }
}
