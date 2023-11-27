package org.whut.trunk.Dao.impl;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.protocol.Resultset;
import org.whut.trunk.Dao.SystemAdminDao;
import org.whut.trunk.Util.JDBC_UTL;
import org.whut.trunk.entity.SystemAdmin;

import java.sql.*;

public class SystemAdminDaoImpl implements SystemAdminDao {

    @Override
    public SystemAdmin findByUsername(String username) {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "select * from system_admin where username = "+username;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt(1);

                SystemAdmin systemAdmin = new SystemAdmin();    //封装
            }


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }
}
