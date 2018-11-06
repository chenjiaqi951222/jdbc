package com.imooc.jdbc.demo3;

import com.imooc.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class JDBCDemo5 {
    @Test
    /**
     * 删除数据
     */
    public void demo3() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL语句
            String sql = "DELETE FROM user WHERE uid = ?";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setInt(1,1);
            //执行SQL
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("删除成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt,conn);
        }
    }
    @Test
    /**
     * 修改数据
     */
    public void demo2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "UPDATE user SET username = ?,password = ?,name = ? where uid = ?";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1,"www");
            pstmt.setString(2,"123456");
            pstmt.setString(3,"何洁");
            pstmt.setInt(4,3);
            //执行SQL
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("修改成功！");
            } else {
                System.out.println("修改失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(pstmt,conn);
        }


    }
    @Test
    /**
     * 保存数据
     */
    public void demo1() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL语句
            String sql = "INSERT INTO user VALUES (null,?,?,?)";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数的值
            pstmt.setString(1,"sss");
            pstmt.setString(2,"123");
            pstmt.setString(3,"呵呵呵");
            //执行SQL
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt,conn);
        }
    }
}
