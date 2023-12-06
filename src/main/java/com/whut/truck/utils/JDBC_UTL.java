package com.whut.truck.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JDBC_UTL {
    //Class.forName( "com.mysql.jdbc.Driver");
    // 定义数据库连接信息

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getconnection() throws IOException {
        InputStream inputStream = JDBC_UTL.class.getClassLoader().getResourceAsStream("db_connect.xml");
        Properties properties = new Properties();
        properties.loadFromXML(inputStream);
        String url = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
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
