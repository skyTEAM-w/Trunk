package com.test.mysql;

import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.utils.HttpCommunicationLayer;
import org.junit.Test;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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
            if (vehicleStatusDto != null) {
                HttpCommunicationLayer communicationLayer = new HttpCommunicationLayer();
                communicationLayer.connectToPython(vehicleStatus.getVehicle_file().getBinaryStream(), "20231205_120000_1.txt", "test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFind30() {

    }

}
