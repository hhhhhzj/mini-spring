package com.minispring.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 18:49
 */
public interface StatementCallback {

    public Object doInStatement(ResultSet resultSet) throws SQLException;
}
