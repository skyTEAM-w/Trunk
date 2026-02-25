package com.whut.truck.controller;

import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.entity.Sensor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/SensorController")
    public String getSensorData(@RequestParam("VehicleName") String vehicleId, Model model) throws IOException {
        
        if (sensorService.csv_output_one_line(vehicleId, String.valueOf(1)).getMsg() == 0) {
            model.addAttribute("VehicleIDError", "id不存在");
            model.addAttribute("alertMessage", "id不存在，请重新输入");
            return "VehicleSensor";
        }

        List<List<String>> rows = new ArrayList<>();
        
        for (int i = 1; i <= 30; i++) {
            String cycle = String.valueOf(i);
            SensorDto dto = sensorService.csv_output_one_line(vehicleId, cycle);
            if (dto.getMsg() == 1 && dto.getSensor() != null) {
                Sensor sensor = dto.getSensor();
                List<String> row = new ArrayList<>();
                row.add(sensor.getDescribe());
                row.add(sensor.getSetting1());
                row.add(sensor.getSetting2());
                row.add(sensor.getSetting3());
                row.add(sensor.getS1());
                row.add(sensor.getS2());
                row.add(sensor.getS3());
                row.add(sensor.getS4());
                row.add(sensor.getS5());
                row.add(sensor.getS6());
                row.add(sensor.getS7());
                row.add(sensor.getS8());
                row.add(sensor.getS9());
                row.add(sensor.getS10());
                row.add(sensor.getS11());
                row.add(sensor.getS12());
                row.add(sensor.getS13());
                row.add(sensor.getS14());
                row.add(sensor.getS15());
                row.add(sensor.getS16());
                row.add(sensor.getS17());
                row.add(sensor.getS18());
                row.add(sensor.getS19());
                row.add(sensor.getS20());
                row.add(sensor.getS21()); // Removed newline
                rows.add(row);
            }
        }
        
        model.addAttribute("dataList", rows);
        return "VehicleSensor";
    }
}
