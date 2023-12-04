package com.whut.truck.Dao;

import com.whut.truck.entity.Sensor;

import java.io.IOException;

public interface SensorDao {
    public Integer csvSave(Sensor sensor) throws IOException;
    public Sensor findBysensorline(String line) throws IOException;
    public Integer csvDelete(Sensor sensor) throws IOException;

}
