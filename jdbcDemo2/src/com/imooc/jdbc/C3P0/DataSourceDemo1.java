package com.imooc.jdbc.C3P0;

import com.imooc.jdbc.utils.JDBCUtils;
import com.imooc.jdbc.utils.JDBCUtils2;
import com.imooc.jdbc.utils.JDBCUtils3;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 连接池的测试类
 */
public class DataSourceDemo1 {
    @Test
    /**
     * 手动设置连接池
     */
    public void demo1() {
        //创建连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //获得连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //设置连接池的参数
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql:///jdbctest");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            //设置最大连接数
            dataSource.setMaxPoolSize(20);
            //设置初始化时的连接数量
            dataSource.setInitialPoolSize(3);
            //获得连接
            conn = dataSource.getConnection();
            //编写SQL
            String sql = "SELECT * FROM course";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数
            //执行SQL
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
    }
    @Test
    /**
     * 使用配置文件的方式
     */
    public void demo2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //获得连接
//          conn = dataSource.getConnection();
            conn = JDBCUtils3.getConnection();
            //编写SQL
            String sql = "SELECT * FROM user";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数
            //执行SQL
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,conn);
        }
    }
}
