package com.whut.truck.controller;

import com.google.gson.JsonObject;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class DetectionController {

    private final SensorService sensorService;
    private final VehicleStatusService vehicleStatusService;

    public DetectionController(SensorService sensorService, VehicleStatusService vehicleStatusService) {
        this.sensorService = sensorService;
        this.vehicleStatusService = vehicleStatusService;
    }

    @PostMapping("/DetectionTest")
    @ResponseBody
    public String uploadFile(@RequestParam("upload") List<MultipartFile> files,
                             @RequestParam("selectName") String selectName) throws IOException {
        
        boolean uploadSuccess = true;

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String id = null;

            if (fileName != null && fileName.contains("_")) {
                String[] parts = fileName.split("_");
                if (parts.length >= 3) {
                    id = parts[2].substring(0, parts[2].indexOf("."));
                }
            }

            if (id == null) {
                // Handle error
                continue;
            }

            ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());

            if ("sensor".equals(selectName)) {
                sensorService.csv_save(inputStream);
            } else if ("vibration".equals(selectName)) {
                VehicleStatusDto vehicleStatusDto = vehicleStatusService.find(id);
                if (vehicleStatusDto.getMsg() == 0) {
                    VehicleStatus vehicleStatus = new VehicleStatus(id, "null", 1, "null", "null", "null", null);
                    vehicleStatusService.save(vehicleStatus);
                }
                vehicleStatusService.Insertfile(id, inputStream);
            } else {
                uploadSuccess = false;
            }
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("uploadSuccess", uploadSuccess);
        return jsonResponse.toString();
    }
}
