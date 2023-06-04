package com.test.service;

import com.minispring.core.Autowired;
import com.minispring.jdbc.core.JdbcTemplate;
import com.minispring.jdbc.core.RowMapper;
import com.test.service.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:24
 */
public class OrderService {

    private JdbcTemplate jdbcTemplate;

    public List<Order> getUserInfo(int orderId) {
        String sql = "select * from t_order where id=?";

        return jdbcTemplate.query(sql, new Object[]{orderId}, new RowMapper<Order>() {
                    @Override
                    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Order rtnOrder  = new Order();
                        rtnOrder.setId(rs.getInt("id"));
                        rtnOrder.setProduct_name(rs.getString("product_name"));
                        rtnOrder.setCreateTime(new java.util.Date(rs.getDate("create_time").getTime()));
                        return rtnOrder;
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