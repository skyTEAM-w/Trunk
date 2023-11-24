package com.test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcTest {
    public static void main(String[] args) throws SQLException {
        // 1. 加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Dr`iver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?serverTimezone=UTC&useSSL=false", "root", "040331");
        System.out.println(connection);
        String sql = "insert into users(id, password) value(123, 123456)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        connection.close();
    }
}
