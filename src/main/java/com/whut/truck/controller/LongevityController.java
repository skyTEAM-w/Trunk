package com.whut.truck.controller;

import com.google.gson.JsonObject;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.utils.HttpCommunicationLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class LongevityController {

    private final SensorService sensorService;
    private final VehicleStatusService vehicleStatusService;
    private final HttpCommunicationLayer communicationLayer;

    public LongevityController(SensorService sensorService, VehicleStatusService vehicleStatusService, HttpCommunicationLayer communicationLayer) {
        this.sensorService = sensorService;
        this.vehicleStatusService = vehicleStatusService;
        this.communicationLayer = communicationLayer;
    }

    @PostMapping("/Longevity")
    public String longevity(@RequestParam("VehicleName") String vehicleId, Model model) throws IOException {
        SensorDto sensorDto = sensorService.csv_find(vehicleId);
        VehicleStatusDto vehicleStatusDto = vehicleStatusService.find(vehicleId);

        if (vehicleStatusDto.getMsg() == 0) {
            model.addAttribute("alertMessage", "车辆编号不存在");
            return "Longevity";
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        JsonObject gson = communicationLayer.connectToPython(sensorDto.getSensorStream(), formattedDateTime + "_" + vehicleId + ".txt", "predict");

        if (gson != null && gson.has("result")) {
            String longevity = gson.get("result").getAsString();
            VehicleStatus vehicleStatus = vehicleStatusDto.getVehicleStatus();
            try {
                vehicleStatus.setEstimated_maintenance_time((int) Double.parseDouble(longevity));
            } catch (NumberFormatException e) {
                // Handle parsing error
                model.addAttribute("alertMessage", "预测结果格式错误: " + longevity);
                return "Longevity";
            }
            vehicleStatusService.save(vehicleStatus);
            model.addAttribute("Longevity", longevity);
        } else {
            model.addAttribute("alertMessage", "预测服务无响应");
        }

        return "Longevity";
    }
}
