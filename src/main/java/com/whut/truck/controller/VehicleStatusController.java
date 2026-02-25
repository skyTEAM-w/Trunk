package com.whut.truck.controller;

import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class VehicleStatusController {

    private final VehicleStatusService vehicleStatusService;

    public VehicleStatusController(VehicleStatusService vehicleStatusService) {
        this.vehicleStatusService = vehicleStatusService;
    }

    @PostMapping("/VehicleStatusController") // Legacy mapping to support existing form action
    public String getStatus(@RequestParam("VehicleName") String vehicleId, Model model) throws IOException {
        VehicleStatusDto vehicleStatusDto = vehicleStatusService.find(vehicleId);

        if (vehicleStatusDto.getMsg() == 0) {
            model.addAttribute("alertMessage", "车辆编号不存在，请重新输入");
            return "VehicleStatus";
        } else {
            VehicleStatus status = vehicleStatusDto.getVehicleStatus();
            model.addAttribute("Data1", status.getMaintenance_status() + "\n");
            model.addAttribute("Data2", status.getEstimated_maintenance_time() + "\n");
            model.addAttribute("Data3", status.getPrevious_failure_status() + "\n");
            model.addAttribute("Data4", status.getLast_Maintenance_date() + "\n");
            model.addAttribute("Data5", status.getMaintenance_Frequency());
            return "VehicleStatus";
        }
    }
}
