package com.whut.truck.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_UTL {
    //Class.forName( "com.mysql.jdbc.Driver");
    // 定义数据库连接信息
    private static String url = "jdbc:mysql://localhost:3306/game";
    private static String username = "root";
    private static String password = "1234";


    public static Connection getconnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    public static void release(Connection connection, Statement statement, ResultSet resultset) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultset != null) {
                resultset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] arg) {
        //System.out.println(JDBC_UTL.getconnection());
    }
}
