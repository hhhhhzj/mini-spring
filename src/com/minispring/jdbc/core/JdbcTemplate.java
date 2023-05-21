package com.minispring.jdbc.core;

import com.minispring.jdbc.datasource.PoolDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-17 15:15
 */
public class JdbcTemplate {

    private PoolDataSource dataSource;

    public JdbcTemplate() {
    }

    public Object query(String sql, Object[] args, StatementCallback stmCallback) {
        Connection con = null;
        ResultSet rs = null;
        Object rtnObj = null;

        try {
            //https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html#connector-j-examples-connection-drivermanager
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            con = DriverManager.getConnection("jdbc:mysql://10.199.162.255:3306/vip_vorder?user=vipshop&password=vipshop");
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter.setValues(args, pstmt);
            rs = pstmt.executeQuery();

            //调用返回数据处理方法，由程序员自行实现
            return stmCallback.doInStatement(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                dataSource.releaseConnect(con);
            } catch (Exception e) {
            }
        }
        return rtnObj;
    }

    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Object rtnObj = null;
        RowMapperResultSetExtractor<T> resultExtractor = new RowMapperResultSetExtractor<>(rowMapper);
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ArgumentPreparedStatementSetter.setValues(args, pstmt);
            rs = pstmt.executeQuery();

            //调用返回数据处理方法，由程序员自行实现
            return resultExtractor.extractData(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return new ArrayList<T>();
    }

    public PoolDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(PoolDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
