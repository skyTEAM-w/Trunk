package com.whut.truck.Dao.impl;

import com.whut.truck.entity.Sensor;
import com.whut.truck.utils.CsvUtil;
import com.whut.truck.utils.JDBC_UTL;
import com.whut.truck.Dao.SensorDao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SensorDaoImpl implements SensorDao{
    @Override
    public Integer csvSave(InputStream inputStream) throws IOException {                   //将csv保存进数据库
        Connection connection = JDBC_UTL.getconnection();
        String sql_check = "SELECT * FROM `game`.`传感器数据` where `车辆id` = ? and `运行轮数` = ?";
        PreparedStatement statement_check = null;
        Integer result = null;

        String line;
        String csvSplitBy = " ";
        String[] data = new String[0];

        int counter = 1; // 计数器，用于限制读取的行数

        PreparedStatement statement = null;
        PreparedStatement updateStmt = null;
        String count1 = null;
        String count2 = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null ) {
                System.out.println(line);
                String id = null;
                String times = null;
                if (counter != 0) {
                    data = line.split(csvSplitBy);
                    id = data[0];
                    times = data[1];
                    statement_check = connection.prepareStatement(sql_check);
                    statement_check.setString(1, id);
                    statement_check.setString(2, times);
                    ResultSet rs = statement_check.executeQuery();
                    if (rs.next()) {
                        count1 = rs.getString(1);
                        count2 = rs.getString(2);
                    }
                    if (count1 == null && count2 == null) {
                        String sql_insert = "INSERT INTO `game`.`传感器数据`(`列数`,`车辆id`, `运行轮数`, `setting1`, `setting2`, `setting3`, `风扇进口总温度`, `低压压气机出口总温度`, `高压压气机出口总温度`, `低压涡轮出口总温度`, `风扇进口压强`, `旁路管道总压强`, `高压压气机出口总压强`, `风扇物理转速`, `核心机物理转速`, `发动机压力比`, `高压压气机出口静压(Ps30)`, `燃油流量与高压压气机出口静压的比率`, `风扇换算转速`, `核心机换算转速`, `涵道比`, `燃烧器油气比`, `抽汽焓`, `需求风扇转速`, `需求风扇换算转速`, `高压涡轮冷气流量`, `低压涡轮冷气流量`) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        statement = connection.prepareStatement(sql_insert);

                        for (int i = 1; i <= 27; i++) {
                            statement.setString(i, data[i - 1]);
                        }
                        result = statement.executeUpdate();
                    } else {
                        String sql_update = "UPDATE `game`.`传感器数据` set `车辆id` = ?, `运行轮数` =?, `setting1`=?, `setting2`=?, `setting3`=?, `风扇进口总温度`=?, `低压压气机出口总温度`=?, `高压压气机出口总温度`=?, `低压涡轮出口总温度`=?, `风扇进口压强`=?, `旁路管道总压强`=?, `高压压气机出口总压强`=?, `风扇物理转速`=?, `核心机物理转速`=?, `发动机压力比`=?, `高压压气机出口静压(Ps30)`=?, `燃油流量与高压压气机出口静压的比率`=?, `风扇换算转速`=?, `核心机换算转速`=?, `涵道比`=?, `燃烧器油气比`=?, `抽汽焓`=?, `需求风扇转速`=?, `需求风扇换算转速`=?, `高压涡轮冷气流量`=?, `低压涡轮冷气流量`=? where `车辆id` = ? and `运行轮数` =?";
                        updateStmt = connection.prepareStatement(sql_update);
                        updateStmt.setString(27, id);
                        updateStmt.setString(28, times);

                        for (int i = 1; i <= 26; i++) {
                            updateStmt.setString(i, data[i-1]);
                        }
                        result = updateStmt.executeUpdate();
                    }
                }
                System.out.println("插入了" + counter + "行");
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (count1 == null && count2 == null) {
                JDBC_UTL.release(connection, statement, null);
            }else {
                JDBC_UTL.release(connection, updateStmt, null);
            }
        }
        return result;
    }



    @Override
    public InputStream findBysensorid(String id) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "SELECT * FROM `game`.`传感器数据` where `车辆id` =  '"+id+"' ORDER BY `列数` DESC LIMIT 30";     //车辆id
        PreparedStatement statement = null;
        List<Sensor> List = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            return CsvUtil.resultSetToCSV(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Integer csvDelete(Sensor sensor) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        //String sql = "DELETE FROM `game`.`传感器数据` where `车辆id` between '0' and '30'";    //删除数据库中csv的前30行
        String sql = "truncate table `game`.`传感器数据`";
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

    @Override
    public Sensor findByid_output_oneline(String id, String cycle) throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "SELECT * FROM `game`.`传感器数据` where `车辆id` = ? and `运行轮数` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,cycle);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String[] values = new String[27];
                for (int i = 1; i <= 27; i++) {
                    values[i - 1] = resultSet.getString(i);
                }
                return new Sensor(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15], values[16], values[17], values[18], values[19], values[20], values[21], values[22], values[23], values[24], values[25], values[26]);    //封装   // 将每个结果添加到结果集中
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return null;
    }
}
