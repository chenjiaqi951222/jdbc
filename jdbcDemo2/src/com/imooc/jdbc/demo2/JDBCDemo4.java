package com.imooc.jdbc.demo2;

import com.imooc.jdbc.utils.JDBCUtils;
import com.imooc.jdbc.utils.JDBCUtils2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo4 {
    @Test
    public void demo1(){
        Connection conn = null;
        Statement stmt = null;
        try {
            // 获得连接:
            conn = JDBCUtils.getConnection();
            //创建执行SQL语句的对象
            stmt = conn.createStatement();
            //编写SQL语句
            String sql = "INSERT user VALUES (null,'fff','156','略略略')";
            //执行SQL
            boolean flag = stmt.execute(sql);
            if (flag == true) {
                System.out.println("失败！");
            } else {
                System.out.println("成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.release(stmt, conn);
        }
    }
    @Test

    public void jdbcUtilTest() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = JDBCUtils2.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM goods";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println( resultSet.getInt("id")+resultSet.getString("name"));

            }
            resultSet = statement.executeQuery("SELECT  * FROM goods WHERE name = '冰箱'");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id")+resultSet.getString("name"));
            }
            int flag = statement.executeUpdate("UPDATE goods SET price = 5000 WHERE name='手机'");
            System.out.println("flag="+flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
