package com.whut.truck.Dao;

import com.whut.truck.entity.Sensor;

import java.io.IOException;
import java.io.InputStream;

public interface SensorDao {
    // 将CSV文件中数据保存到数据库
    /**
     *
     * @param inputStream  文件流
     * @return Integer错误信息
     * @throws IOException
     */
    public Integer csvSave(InputStream inputStream) throws IOException;
    // 根据传感器列数查找对应的传感器对象
    /**
     * @param id 列数
     * @return 错误参数
     * @throws IOException
     */
    public InputStream findBysensorid(String id) throws IOException;
    // 从数据库删除CSV数据
    /**
     *
     * @param sensor Sensor类
     * @return Integer错误信息
     * @throws IOException
     */
    public Integer csvDelete(Sensor sensor) throws IOException;
}
