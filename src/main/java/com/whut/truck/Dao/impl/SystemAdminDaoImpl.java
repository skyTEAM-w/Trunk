package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.SystemAdminDao;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.utils.JDBC_UTL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdminDaoImpl implements SystemAdminDao {
    @Override
    public SystemAdmin findByUsername(String username) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "SELECT * FROM `game`.`用户` where `用户名` = '" + username + "'";    //用username进行查询
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);

                return new SystemAdmin(id, username, password, email);    //封装
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Integer save(SystemAdmin systemAdmin) throws IOException {          //注册用户信息
        Connection connection = JDBC_UTL.getconnection();
        //String alterQuery = "SELECT MAX(用户id) + 1 FROM `game`.`用户`";
        String sql = "insert into `game`.`用户`(用户名, 密码, 邮箱)values (?,?,?)";

        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, systemAdmin.getUsername());
            statement.setString(2, systemAdmin.getPassword());
            statement.setString(3, systemAdmin.getEmail());
            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBC_UTL.release(connection, statement, null);
        }
        return result;
    }
}
