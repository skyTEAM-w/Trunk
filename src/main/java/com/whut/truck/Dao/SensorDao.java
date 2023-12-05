package com.whut.truck.Dao;

import com.whut.truck.entity.Sensor;

import java.io.IOException;

public interface SensorDao {
    // 将CSV文件中数据保存到数据库
    /**
     *
     * @param sensor Sensor类
     * @return Integer错误信息
     * @throws IOException
     */
    public Integer csvSave(Sensor sensor) throws IOException;
    // 根据传感器列数查找对应的传感器对象
    /**
     *
     * @param line 列数
     * @return Sensor类
     * @throws IOException
     */
    public Sensor findBysensorline(String line) throws IOException;
    // 从数据库删除CSV数据
    /**
     *
     * @param sensor Sensor类
     * @return Integer错误信息
     * @throws IOException
     */
    public Integer csvDelete(Sensor sensor) throws IOException;
}
