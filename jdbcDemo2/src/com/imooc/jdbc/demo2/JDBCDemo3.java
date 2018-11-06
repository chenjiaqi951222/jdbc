package com.imooc.jdbc.demo2;

import org.junit.Test;

import java.sql.*;

public class JDBCDemo3 {
    @Test
    /**
     * 查询所有记录
     */
    public  void demo3_4() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///jdbctest","root","123456");
            String sql = "SELECT * FROM user";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;
            }

        }
    }
    @Test
    /**
     * 删除操作
     */
    public void  demo3_3() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///jdbctest","root","123456");
            String sql = "DELETE FROM user where uid = 4";
            preparedStatement = conn.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
        }

    }


    @Test
    /**
     *修改操作
     */
    public void demo3_2() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            //加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //获得连接
            conn = DriverManager.getConnection("jdbc:mysql:///jdbctest", "root", "123456");
            //获取执行SQL语句的对象
            String sql = "DELETE FROM goods where name = '手机'";
            preparedStatement = conn.prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功！");
                preparedStatement = conn.prepareStatement("SELECT * FROM goods");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Float price = resultSet.getFloat("price");
                    String desp = resultSet.getNString("desp");
                    System.out.println("id=" + id + "    " + "name=" + name + "   " + "price=" + price + "   " + "desp=" + desp);
                    resultSet.close();
                }
            } else {
                System.out.println("删除失败！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
        }
    }


    /**
     * 保存操作
     */
    @Test


    public void test() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //注册驱动：
            Class.forName("com.mysql.jdbc.Driver");
            //获得连接：
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "123456");
            //获得执行SQL语句的对象
            String sql = "INSERT INTO goods VALUES (null,'耳机','200.0','蓝牙耳机')";
            //执行SQL语句
            preparedStatement = connection.prepareStatement(sql);
            int i = preparedStatement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("保存成功");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }
}

