package com.whut.truck.Service;

import com.whut.truck.Dto.SensorDto;
import com.whut.truck.entity.Sensor;

import java.io.IOException;
import java.io.InputStream;

public interface SensorService {
    //通过id查找数据库的csv数据

    /**
     *
     * @param id 车辆id
     * @return SensorDto错误信息
     * @throws IOException
     */
    public SensorDto csv_find(String id) throws IOException;

    //将CSV文件中数据保存到数据库

    /**
     *
     * @param inputStream 文件流
     * @return SensorDto错误信息
     * @throws IOException
     */
    public SensorDto csv_save(InputStream inputStream) throws IOException;


    //将CSV文件中数据从数据库删除

    /**
     *
     * @param sensor Sensor类
     * @return SensorDto错误信息
     * @throws IOException
     */
    public SensorDto csv_delete(Sensor sensor) throws IOException;

    /**
     *
     * @param id   id查找数据库对应行               输出一行数据
     * @return SensorDto错误信息
     * @throws IOException
     */
    public SensorDto csv_output_one_line(String id, String cycle) throws IOException;
}
