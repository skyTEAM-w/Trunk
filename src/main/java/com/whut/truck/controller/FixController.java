package com.whut.truck.controller;

import com.google.gson.JsonObject;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.utils.HttpCommunicationLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class FixController {

    private final VehicleStatusService vehicleStatusService;
    private final HttpCommunicationLayer communicationLayer;

    public FixController(VehicleStatusService vehicleStatusService, HttpCommunicationLayer communicationLayer) {
        this.vehicleStatusService = vehicleStatusService;
        this.communicationLayer = communicationLayer;
    }

    @PostMapping("/Fix")
    public String fix(@RequestParam("VehicleName") String vehicleId, Model model) throws IOException {
        VehicleStatusDto vehicleStatusDto = vehicleStatusService.find(vehicleId);

        if (vehicleStatusDto.getMsg() == 0) {
            model.addAttribute("alertMessage", "车辆编号不存在");
            return "Fix";
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        JsonObject gson = null;
        try {
            gson = communicationLayer.connectToPython(vehicleStatusDto.getVehicleStatus().getVehicle_file().getBinaryStream(), formattedDateTime + "_" + vehicleId + ".txt", "classify");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (gson != null && gson.has("result")) {
            String wrong = gson.get("result").getAsString();
            VehicleStatus temp = vehicleStatusDto.getVehicleStatus();
            if (gson.has("test_time")) {
                temp.setLast_Maintenance_date(gson.get("test_time").getAsString());
            }
            temp.setPrevious_failure_status(wrong);
            temp.setMaintenance_Frequency(String.valueOf(Integer.parseInt(temp.getMaintenance_Frequency()) + 1));
            
            if (Integer.parseInt(wrong) <= 2) {
                temp.setMaintenance_status("正常");
            } else {
                temp.setMaintenance_status("故障");
            }
            vehicleStatusService.save(temp);
            model.addAttribute("Wrong", wrong);
        } else {
            model.addAttribute("alertMessage", "预测服务无响应");
        }

        return "Fix";
    }
}
