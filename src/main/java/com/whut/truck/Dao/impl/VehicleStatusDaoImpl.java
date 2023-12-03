package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.utils.JDBC_UTL;
import com.whut.truck.entity.VehicleStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.*;

public class VehicleStatusDaoImpl implements VehicleStatusDao {
    @Override
    //查询车辆id
    public VehicleStatus findByVehicle_id(String vehicle_id) throws IOException {          //用vehicle_id进行查询
        Connection connection = JDBC_UTL.getconnection();
        String sql_v = "SELECT * FROM `game`.`车辆状态` where `车辆id` = '"+vehicle_id+"'";
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
                String Last_Maintenance_date = resultSet.getString(5);
                String Maintenance_Frequency = resultSet.getString(6);
                Blob Vehicle_file = resultSet.getBlob(7);

                return new VehicleStatus(vehicle_id, maintenance_status, estimated_maintenance_time, previous_failure_status, Last_Maintenance_date, Maintenance_Frequency, Vehicle_file);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }

    //添加车辆信息
    public Integer save(VehicleStatus vehicleStatus) throws IOException {                 //添加车辆信息
        Connection connection = JDBC_UTL.getconnection();
        //String alterQuery = "SELECT MAX(用户id) + 1 FROM `game`.`用户`";
        String sql = "insert into `game`.`车辆状态`(维护状态, 剩余维护时间（分钟）, 故障状态, 上次维护时间， 维护次数, 数据文件)values (?,?,?,?,?,?)";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, vehicleStatus.getMaintenance_status());
            statement.setInt(2, vehicleStatus.getEstimated_maintenance_time());
            statement.setString(3, vehicleStatus.getPrevious_failure_status());
            statement.setString(4, vehicleStatus.getLast_Maintenance_date());
            statement.setString(5, vehicleStatus.getMaintenance_Frequency());
            statement.setBlob(6, vehicleStatus.getVehicle_file());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_UTL.release(connection, statement, null);
        }
        return result;
    }

    @Override            //更改文件
    public Integer Insert_file(Integer id) throws IOException {
        //InputStream file = new FileInputStream("D:/000001/1/222.txt");
        InputStream file = new FileInputStream("D:/Anaconda3/python311.pdb");

        Connection connection = JDBC_UTL.getconnection();
        PreparedStatement statement = null;

        String sql = "UPDATE `game`.`车辆状态` SET `数据文件` = ? WHERE `车辆id` = '" + id + "'";
        System.out.println(sql);
        Integer result = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setBlob(1, file);
            result = statement.executeUpdate();


            System.out.println("文件已成功添加到数据库中！");
            System.out.println(file);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBC_UTL.release(connection, statement, null);
        }
        return result;
    }
}
