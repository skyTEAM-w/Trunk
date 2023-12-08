package com.whut.truck.Service.impl;

import com.whut.truck.Dao.SensorDao;
import com.whut.truck.Dao.impl.SensorDaoImpl;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.entity.Sensor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SensorServiceImpl implements SensorService {
    private SensorDao sensorDao = new SensorDaoImpl();


    @Override
    public SensorDto csv_find(String id) throws IOException {           //通过列数查找数据库的csv数据
//        List<Sensor> adminList  =null;
//        adminList = this.sensorDao.findBysensorid(id);
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensorStream(this.sensorDao.findBysensorid(id));
//        sensorDto.setList_SystemAdmin(adminList);
        return sensorDto;
    }



    @Override
    public SensorDto csv_save(InputStream inputStream) throws IOException {          //将CSV文件中数据保存到数据库
        Integer Insert = this.sensorDao.csvSave(inputStream);
        SensorDto sensorDto = new SensorDto();
        if (Insert != 1) throw new RuntimeException("传感器信息添加失败");
        return sensorDto;
    }

    @Override
    public SensorDto csv_delete(Sensor sensor) throws IOException {        //将CSV文件中数据从数据库删除
        Integer Insert = this.sensorDao.csvDelete(sensor);
        SensorDto sensorDto = new SensorDto();
        if (Insert == 0) throw new RuntimeException("传感器信息删除失败");
        return sensorDto;
    }

    @Override
    public SensorDto csv_output_one_line(String id, String cycle) throws IOException {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensor(this.sensorDao.findByid_output_oneline(id, cycle));
//        sensorDto.setList_SystemAdmin(adminList);
        return sensorDto;
    }
}
