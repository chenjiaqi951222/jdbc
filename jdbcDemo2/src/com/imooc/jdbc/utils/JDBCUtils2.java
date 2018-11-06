package com.imooc.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils2 {
    private static final String driverClass;
    private static final String url;
    private static final String user;
    private static final String pwd;

    static {
        Properties properties = new Properties();
        InputStream inst = JDBCUtils2.class.getClassLoader().getResourceAsStream("jdbcUtil.properties");
        try {
            properties.load(inst);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        pwd = properties.getProperty("pwd");
    }

    /**
     * 加载驱动程序
     */
    public static void getDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     * 获得连接
     */

    public static Connection getConnection() throws Exception {
        getDriver();
        Connection conn = DriverManager.getConnection(url,user,pwd);
        return  conn;
    }

    /**
     * 资源释放
     */
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    public static void release(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
