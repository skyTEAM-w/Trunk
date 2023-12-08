package com.whut.truck.Dto;

import com.whut.truck.entity.Sensor;

import java.io.InputStream;
import java.util.List;

public class SensorDto {
    //错误信息封装
    private Integer msg;
    // 传感器对象
    private Sensor Sensor;
    private List<Sensor> List_Sensor;

    private InputStream sensorStream;

    public InputStream getSensorStream() {
        return sensorStream;
    }

    public void setSensorStream(InputStream sensorStream) {
        this.sensorStream = sensorStream;
    }

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

    public void setList_SystemAdmin(List<com.whut.truck.entity.Sensor> adminList) {
    }

    public List<com.whut.truck.entity.Sensor> getList_Sensor() {
        return List_Sensor;
    }

    public void setList_Sensor(List<com.whut.truck.entity.Sensor> list_Sensor) {
        List_Sensor = list_Sensor;
    }
}
