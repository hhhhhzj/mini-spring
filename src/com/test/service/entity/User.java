package com.test.service.entity;

import java.util.Date;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:18
 */
public class User {
    private int id;
    private String userName;
    private Integer age;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
