package com.whut.truck.Dto;

import com.whut.truck.entity.Sensor;

public class SensorDto {
    //错误信息封装
    private Integer msg;
    // 传感器对象
    private Sensor Sensor;

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public com.whut.truck.entity.Sensor getSensor() {
        return Sensor;
    }

    public void setSensor(com.whut.truck.entity.Sensor sensor) {
        Sensor = sensor;
    }
}
