package com.whut.truck.Service;

import com.whut.truck.Dto.SensorDto;
import com.whut.truck.entity.Sensor;

import java.io.IOException;

public interface SensorService {
    public SensorDto csv_find(String id) throws IOException;
    public SensorDto csv_save(Sensor sensor) throws IOException;
    public SensorDto csv_delete(Sensor sensor) throws IOException;

}
