package com.imooc.jdbc.demo2;

import com.imooc.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

public class JDBCDemo6 {
    @Test
    /**
     * 添加数据
     */
    public void Insert() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获取当前时间（java.sql.Date）
            java.sql.Date date1 = new java.sql.Date(new java.util.Date().getTime());
            //强转时间类型（java.util.Date）
            java.util.Date date2 = new java.util.Date(date1.getTime());
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "INSERT INTO course (name,category,desp,createTime) VALUES(?,?,?,?),(?,?,?,?),(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "JAVA零基础");
            pstmt.setString(2, "java");
            pstmt.setString(3, "JAVA基础语法");
            pstmt.setTimestamp(4, new Timestamp(date2.getTime()));
            pstmt.setString(5, "JAVA Web");
            pstmt.setString(6, "Java");
            pstmt.setString(7, "JSP和Servlet");
            pstmt.setTimestamp(8, new Timestamp(date2.getTime()));
            pstmt.setString(9, "前端小白");
            pstmt.setString(10, "前端");
            pstmt.setString(11, "HTML/CSS/JS");
            pstmt.setTimestamp(12, new Timestamp(date2.getTime()));
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Test
    /**
     * 显示所有数据
     */
    public void show() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //获得连接
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM course";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("课程名字：" + rs.getString("name") + "  " + "方向:" + rs.getString("category") +
                        "   " + "描述:" + rs.getString("desp") + "   " + "创建时间：" + rs.getTime("createTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }

    }

    @Test
    /**
     * 修改操作
     */
    public void update() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "UPDATE course SET name = ? WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Java语法");
            pstmt.setString(2, "Java零基础");
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 删除操作
     */
    public void delete() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "DELETE FROM course WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Java Web");
            int num = pstmt.executeUpdate();
            if (num > 0) {
                System.out.println("删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }

    @Test
    /**
     * 按照时间排序地址
     */
    public void showDESCTime() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "select * FROM course order by createTime ";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + "     " + rs.getTimestamp("createTime"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }

    }

    /**
     * 查询所有数据
     */
    @Test
    public void selectTest() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "SELECT * FROM user";
            //预编译SQL
            psmt = conn.prepareStatement(sql);
            //设置参数
            //执行SQL
            rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("uid")+"   "+rs.getString("username")+"    "+
                        rs.getString("password")+"   "+rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs,psmt,conn);
        }
    }
    /**
     * 查询一条记录
     */
    @Test
    public void selectTest2() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs =null;
        try {
            //获得连接
            conn = JDBCUtils.getConnection();
            //编写SQL
            String sql = "SELECT * FROM user WHERE uid = ?";
            //预编译SQL
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setInt(1,13);
            //执行SQL；
            rs = pstmt.executeQuery();
            //判断结果集
            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
    }
}
