package com.mjt.test;

import com.mjt.pojo.Kind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        //准备分类测试数据：

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/story?useUnicode=true&characterEncoding=utf8",
                    "root", "admin");
            Statement s = c.createStatement();
        ){

            String sqlFormat = "insert into bookrack values (null, 1)";
            String sql = String.format(sqlFormat);
            s.execute(sql);

            System.out.println("已经成功创建测试数据");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
