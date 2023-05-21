package com.minispring.batis;

import com.minispring.batis.MapperNode;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 14:22
 */
public interface SqlSessionFactory {

    public SqlSession openSession();

    MapperNode getMapperNode(String name);
}
