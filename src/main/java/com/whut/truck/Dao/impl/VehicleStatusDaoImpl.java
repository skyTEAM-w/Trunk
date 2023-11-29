package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.utils.JDBC_UTL;
import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleStatusDaoImpl implements VehicleStatusDao {
    @Override
    //查询车辆id
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

    //添加车辆信息
    public Integer save(VehicleStatus vehicleStatus) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        //String alterQuery = "SELECT MAX(用户id) + 1 FROM `game`.`用户`";
        String sql = "insert into `game`.`车辆状态`(维护状态, 剩余维护时间（分钟）, 故障状态)values (?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, vehicleStatus.getMaintenance_status());
            statement.setInt(2, vehicleStatus.getEstimated_maintenance_time());
            statement.setString(3, vehicleStatus.getPrevious_failure_status());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_UTL.release(connection, statement, null);
        }
        return result;
    }
}
