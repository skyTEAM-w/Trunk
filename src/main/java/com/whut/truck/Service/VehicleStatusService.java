package com.whut.truck.Service;

import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.io.InputStream;

public interface VehicleStatusService {
    /**
     *
     * @param vehicle_id  车辆id
     * @return VehicleStatusDto 错误信息
     * @throws IOException
     */
    //根据车辆id查找车辆数据
    public VehicleStatusDto find(String vehicle_id) throws IOException;

    /**
     *
     * @param vehicleStatus VehicleStatus类
     * @return VehicleStatusDto 错误信息
     * @throws IOException
     */

    //添加车辆数据
    public VehicleStatusDto save(VehicleStatus vehicleStatus) throws IOException;

    /**
     *
     * @param id  车辆id
     * @param inputStream  文件流
     * @return VehicleStatusDto 错误信息
     * @throws IOException
     */
    public VehicleStatusDto Insertfile(String id, InputStream inputStream) throws IOException;

}
