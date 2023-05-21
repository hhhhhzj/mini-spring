package com.test.service;

import com.minispring.jdbc.core.JdbcTemplate;
import com.minispring.jdbc.core.RowMapper;
import com.test.service.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:24
 */
public class UserService {

    private JdbcTemplate jdbcTemplate;

    public List<User> getUserInfo(int userId) {



        String sql = "select * from t_user where user_id=?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User rtnUser = new User();
                        rtnUser.setId(rs.getInt("user_id"));
                        rtnUser.setUserName(rs.getString("user_name"));
                        rtnUser.setAge(rs.getInt("age"));
                        return rtnUser;
                    }
                });
    }




    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}

