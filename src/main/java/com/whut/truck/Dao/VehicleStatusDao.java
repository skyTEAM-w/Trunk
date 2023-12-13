package com.whut.truck.Dao;

import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface VehicleStatusDao {
    //根据车辆id查找车辆数据

    /**
     *
     * @param vehicle_id 车辆id
     * @return VehicleStatus类
     * @throws IOException
     */
    public VehicleStatus findByVehicle_id(String vehicle_id) throws IOException;

    /**
     *
     * @param vehicleStatus VehicleStatus类
     * @return Integer
     * @throws IOException
     */

    //添加车辆数据
    public Integer save(VehicleStatus vehicleStatus) throws IOException;

    //根据车辆id插入数据文件

    /**
     *
     * @param id  车辆id
     * @param inputStream  文件流
     * @return Integer
     * @throws IOException
     */
    public Integer Insert_file(String id, InputStream inputStream) throws IOException;
}
