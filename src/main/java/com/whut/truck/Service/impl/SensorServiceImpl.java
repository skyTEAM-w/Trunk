package com.whut.truck.Service.impl;

import com.whut.truck.Dao.SensorDao;
import com.whut.truck.Dao.impl.SensorDaoImpl;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.entity.Sensor;

import java.io.IOException;

public class SensorServiceImpl implements SensorService {
    private SensorDao sensorDao = new SensorDaoImpl();


    @Override
    public SensorDto csv_find(String line) throws IOException {
        Sensor sensor  = this.sensorDao.findBysensorline(line);
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensor(sensor);
        return sensorDto;
    }

    @Override
    public SensorDto csv_save(Sensor sensor) throws IOException {
        Integer Insert = this.sensorDao.csvSave(sensor);
        SensorDto sensorDto = new SensorDto();
        if(Insert != 1) throw new RuntimeException("传感器信息添加失败");
        return sensorDto;
    }
    @Override
    public SensorDto csv_delete(Sensor sensor) throws IOException {
        Integer Insert = this.sensorDao.csvDelete(sensor);
        SensorDto sensorDto = new SensorDto();
        if(Insert == 0) throw new RuntimeException("传感器信息删除失败");
        return sensorDto;
    }
}
