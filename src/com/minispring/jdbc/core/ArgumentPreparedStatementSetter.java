package com.minispring.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 10:18
 */
public class ArgumentPreparedStatementSetter {

    public static void setValues(Object[] args, PreparedStatement pstmt) throws SQLException {
        for (int i = 0; i < args.length; i++) { //设置参数
            Object arg = args[i];            //按照不同的数据类型调用JDBC的不同设置方法
            if (arg instanceof String) {
                pstmt.setString(i + 1, (String) arg);
            } else if (arg instanceof Integer) {
                pstmt.setInt(i + 1, (int) arg);
            }
        }
    }
}
