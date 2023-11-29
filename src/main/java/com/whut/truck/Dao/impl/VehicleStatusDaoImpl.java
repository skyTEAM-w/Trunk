package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Util.JDBC_UTL;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleStatusDaoImpl implements VehicleStatusDao {
    @Override
    public VehicleStatus findByVehicle_id(String vehicle_id) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql_v = "SELECT * FROM `game`.`车辆状态` where `车辆id` = '"+vehicle_id+"'";     //用vehicle_id进行查询
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql_v);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                vehicle_id = resultSet.getString(1);
                String maintenance_status = resultSet.getString(2);
                Integer estimated_maintenance_time = resultSet.getInt(3);
                String previous_failure_status = resultSet.getString(4);
                //list.add(new VehicleStatus(vehicle_id, maintenance_status, estimated_maintenance_time, previous_failure_status));
                return new VehicleStatus(vehicle_id, maintenance_status, estimated_maintenance_time, previous_failure_status);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }
}
