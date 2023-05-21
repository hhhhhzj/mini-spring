package com.minispring.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 11:03
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}