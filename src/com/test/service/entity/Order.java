package com.test.service.entity;

import java.util.Date;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:18
 */
public class Order {
    private int id;
    private String product_name;
    private Date createTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
