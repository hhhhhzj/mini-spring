package com.test.service;

import com.minispring.batis.DefaultSqlSessionFactory;
import com.minispring.batis.SqlSession;
import com.minispring.batis.SqlSessionFactory;
import com.test.service.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Zhijian.H
 * @since 2023/5/21 下午1:33
 */
public class UserServiceV2 {

    private DefaultSqlSessionFactory sqlSessionFactory;

    public User getUserInfo(int userid) {
        //final String sql = "select id, name,birthday from users where id=?";
        String sqlid = "com.test.service.UserServiceV2.getUserInfo";

        ((DefaultSqlSessionFactory) sqlSessionFactory).init();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return (User)sqlSession.selectOne(sqlid, new Object[]{new Integer(userid)},
                (rs)->{
                    User rtnUser = new User();
                    if (rs.next()) {
                        rtnUser.setId(rs.getInt("user_id"));
                        rtnUser.setUserName(rs.getString("user_name"));
                        rtnUser.setAge(rs.getInt("age"));
                    } else {
                    }
                    return rtnUser;
                }
        );
    }

    public DefaultSqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(DefaultSqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}