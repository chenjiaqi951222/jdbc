package com.imooc.jdbc.demo1;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import javax.xml.transform.Result;
import java.sql.*;

public class JDBCDemo1 {
    @Test
    /**
     * JDBC入门程序
     */
    public void demo1() {
        Connection Connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try { //1.加载驱动
//            DriverManager.registerDriver(new Driver());//会注册两次驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得连接
            Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "123456");
            //3.创建执行SQL语句的对象，并且执行SQL
            //3.1创建执行sql对象
            String sql = "SELECT * FROM user";
            statement = Connection.createStatement();
            //3.2执行sql
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                System.out.println("uid=" + uid);
                System.out.println("username=" + username);
                System.out.println("password=" + password);
                System.out.println("name" + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    // ignore
                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    // ignore
                }
                statement = null;
            }
            if (Connection != null) {
                try {
                    Connection.close();
                } catch (SQLException ex){
                    //ignore
                }
                Connection = null;
            }

        }
    }
}
