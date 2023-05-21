package com.minispring.jdbc.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * description
 *  参考: https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html#connector-j-examples-connection-drivermanager
 *
 * @author zhijian05.huang
 * @date 2023-05-18 10:03
 */
public class PoolDataSource implements DataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Properties connectionProperties;

    private int initialSize = 3;

    private List<Connection> idleConnections;
    private List<Connection> busyConnections;

    //默认构造函数
    public PoolDataSource() {
    }

    private void initPool() throws SQLException {
        this.idleConnections = new LinkedList<>();
        this.busyConnections = new LinkedList<>();
                setDriverClassName(driverClassName);
        for(int i = 0; i < initialSize; i++) {
            Connection connect = getConnectionFromDriver(getUsername(), getPassword());
            this.idleConnections.add(connect);
        }
    }

    private Connection getAvailableConnection() throws SQLException {
        if (idleConnections == null) {
            initPool();
        }
        Connection connection = idleConnections.get(0);
        busyConnections.add(connection);
        idleConnections.remove(connection);
        ////这里搞个检测,
        return connection;
    }

    public void releaseConnect(Connection connection) {
        busyConnections.remove(connection);
        idleConnections.add(connection);
    }

    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        return null;
    }

    //实际建立数据库连接
    @Override
    public Connection getConnection() throws SQLException {
        return getAvailableConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnectionFromDriver(username, password);
    }

    //将参数组织成Properties结构，然后拿到实际的数据库连接
    protected Connection getConnectionFromDriver(String username, String password) throws SQLException {
        Properties mergedProps = new Properties();
        Properties connProps = getConnectionProperties();
        if (connProps != null) {
            mergedProps.putAll(connProps);
        }
        if (username != null) {
            mergedProps.setProperty("user", username);
        }
        if (password != null) {
            mergedProps.setProperty("password", password);
        }

       return getConnectionFromDriverManager(getUrl(),mergedProps);
    }
    //通过DriverManager.getConnection()建立实际的连接
    protected Connection getConnectionFromDriverManager(String url, Properties props) throws SQLException {
        return DriverManager.getConnection(url, props);
    }

    //设置driver class name的方法，要加载driver类
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        try {
            Class.forName(this.driverClassName).newInstance();
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load JDBC driver class [" + driverClassName + "]", ex);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //一下是属性相关的getter和setter
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Properties getConnectionProperties() {
        return connectionProperties;
    }
    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }
    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
    @Override
    public void setLogWriter(PrintWriter arg0) throws SQLException {
    }
    @Override
    public void setLoginTimeout(int arg0) throws SQLException {
    }
    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return false;
    }
}