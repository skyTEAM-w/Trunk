package com.test.mysql;

import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.Sensor;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.utils.CsvUtil;
import com.whut.truck.utils.HttpCommunicationLayer;
import com.whut.truck.utils.JDBC_UTL;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class blobToPython {
    @Test
    public void Test() throws SQLException, IOException {
        VehicleStatusService statusService = new VehicleStatusServiceImpl();
        VehicleStatusDto vehicleStatusDto = null;
        VehicleStatus vehicleStatus = null;
        try {
            vehicleStatusDto = statusService.Insertfile(1, blobToPython.class.getClassLoader().getResourceAsStream("jsonTest.txt"));
            vehicleStatusDto = statusService.find(String.valueOf(1));
            vehicleStatus = vehicleStatusDto.getVehicleStatus();
            HttpCommunicationLayer communicationLayer = new HttpCommunicationLayer();
            communicationLayer.connectToPython(vehicleStatus.getVehicle_file().getBinaryStream(), "20231205_120000_1.txt", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFind30() throws IOException {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "SELECT * FROM `game`.`传感器数据` where `车辆id` =  '"+1+"' ORDER BY `运行轮数` DESC LIMIT 30";     //车辆id
        PreparedStatement statement = null;
        List<Sensor> List = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            System.out.println(CsvUtil.resultSetToCSV(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
