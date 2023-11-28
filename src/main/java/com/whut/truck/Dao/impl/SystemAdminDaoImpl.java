package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.SystemAdminDao;
import com.whut.truck.Util.JDBC_UTL;
import com.whut.truck.entity.SystemAdmin;

import java.sql.*;

public class SystemAdminDaoImpl implements SystemAdminDao {
    @Override
    public SystemAdmin findByUsername(String username) {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "select * from game.用户 where username = '"+username+"'";    //用username进行查询
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String email = resultSet.getString(4);

                return new SystemAdmin(id, username, password, email);    //封装
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Integer save(SystemAdmin systemAdmin) {
            Connection connection = JDBC_UTL.getconnection();
            String sql = "insert into game.用户(username, password, email)values (?,?,?)";
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
            }finally {
                JDBC_UTL.release(connection, statement, null);
            }
        return null;
    }
}
