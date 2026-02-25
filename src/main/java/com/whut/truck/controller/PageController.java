package com.whut.truck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/hall")
    public String hall() {
        return "Hall";
    }

    @GetMapping("/detection")
    public String detection() {
        return "Detection";
    }

    @GetMapping("/fix")
    public String fix() {
        return "Fix";
    }

    @GetMapping("/longevity")
    public String longevity() {
        return "Longevity";
    }
    
    @GetMapping("/vehicleSensor")
    public String vehicleSensor() {
        return "VehicleSensor";
    }
    
    @GetMapping("/vehicleStatus")
    public String vehicleStatus() {
        return "VehicleStatus";
    }
}
