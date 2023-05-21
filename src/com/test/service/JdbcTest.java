package com.test.service;

import com.test.service.entity.User;
import org.junit.Test;

import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:30
 */
public class JdbcTest {

    @Test
    public void testGetUserInfo() {
        UserService userService = new UserService();
        List<User> user = userService.getUserInfo(234561);
        System.out.println(user.toString());
    }
}
