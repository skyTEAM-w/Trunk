package com.whut.truck.Dao.impl;

import com.whut.truck.entity.Sensor;
import com.whut.truck.utils.JDBC_UTL;
import com.whut.truck.Dao.SensorDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorDaoImpl implements SensorDao{
    @Override
    public Integer csvSave(Sensor sensor) throws IOException {

        Connection connection = JDBC_UTL.getconnection();
        String sql = "INSERT INTO `game`.`传感器数据`(`传感器id`, `传感器描述`, `风扇进口总温度`, `低压压气机出口总温度`, `高压压气机出口总温度`, `低压涡轮出口总温度`, `风扇进口压强`, `旁路管道总压强`, `高压压气机出口总压强`, `风扇物理转速`, `核心机物理转速`, `发动机压力比`, `高压压气机出口静压(Ps30)`, `燃油流量与高压压气机出口静压的比率`, `风扇换算转速`, `核心机换算转速`, `涵道比`, `燃烧器油气比`, `抽汽焓`, `需求风扇转速`, `需求风扇换算转速`, `高压涡轮冷气流量`, `低压涡轮冷气流量`, `setting1`, `setting2`, `setting3`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        Integer result = null;

        System.out.println(sql);
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sensor.getSensor_id());
            statement.setString(2, sensor.getDescribe());

            statement.setString(3, sensor.getS1());
            statement.setString(4, sensor.getS2());
            statement.setString(5, sensor.getS3());
            statement.setString(6, sensor.getS4());
            statement.setString(7, sensor.getS5());
            statement.setString(8, sensor.getS6());
            statement.setString(9, sensor.getS7());
            statement.setString(10, sensor.getS8());
            statement.setString(11, sensor.getS9());
            statement.setString(12, sensor.getS10());
            statement.setString(13, sensor.getS11());
            statement.setString(14, sensor.getS12());
            statement.setString(15, sensor.getS13());
            statement.setString(16, sensor.getS14());
            statement.setString(17, sensor.getS15());
            statement.setString(18, sensor.getS16());
            statement.setString(19, sensor.getS17());
            statement.setString(20, sensor.getS18());
            statement.setString(21, sensor.getS19());
            statement.setString(22, sensor.getS20());
            statement.setString(23, sensor.getS21());
            statement.setString(24, sensor.getSetting1());
            statement.setString(25, sensor.getSetting2());
            statement.setString(26, sensor.getSetting3());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_UTL.release(connection, statement, null);
        }
        return result;
    }


    @Override
    public Sensor findBysensorline(String line) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "SELECT * FROM `game`.`传感器数据` where `列数` = '"+line+"'";    //用username进行查询
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String[] values = new String[27];
                for (int i = 1; i <= 27; i++) {
                    values[i-1]=resultSet.getString(i);
                    System.out.println(values[i-1]);
                }
                return new Sensor(values[0],values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15], values[16], values[17], values[18], values[19], values[20],values[21], values[22], values[23],values[24],values[25],values[26]);    //封装
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Integer csvDelete(Sensor sensor) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "DELETE FROM `game`.`传感器数据` where `传感器id` between '0' and '30'";    //用username进行查询
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC_UTL.release(connection, statement, null);
        }
        System.out.println(result);
        return  result;
    }
}
