package com.whut.truck.Dto;

import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.entity.VehicleStatus;

public class VehicleStatusDto {
    //错误信息封装
    private Integer msg;
    // 车辆对象
    private VehicleStatus vehicleStatus;

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
