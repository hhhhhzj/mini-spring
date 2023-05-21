package com.minispring.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 11:08
 */
public interface ResultSetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}