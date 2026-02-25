package com.whut.truck.Service.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VehicleStatusServiceImpl implements VehicleStatusService {
    
    private final VehicleStatusDao vehicleStatusDao;

    public VehicleStatusServiceImpl(VehicleStatusDao vehicleStatusDao) {
        this.vehicleStatusDao = vehicleStatusDao;
    }

    @Override
    public VehicleStatusDto find(String vehicle_id) throws IOException {
        VehicleStatus vehicleStatus = this.vehicleStatusDao.findByVehicle_id(vehicle_id);
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();

        if (vehicleStatus == null) {
            vehicleStatusDto.setMsg(0);
        } else {
            vehicleStatusDto.setMsg(1);
            vehicleStatusDto.setVehicleStatus(vehicleStatus);
        }
        return vehicleStatusDto;
    }

    @Override
    public VehicleStatusDto save(VehicleStatus vehicleStatus) throws IOException {
        Integer save = this.vehicleStatusDao.save(vehicleStatus);
        if (save != 1) throw new RuntimeException("车辆信息添加失败");
        return null;
    }

    @Override
    public VehicleStatusDto Insertfile(String id, InputStream inputStream) throws IOException {
        Integer Insert = this.vehicleStatusDao.Insert_file(id, inputStream);
        if (Insert != 1) throw new RuntimeException("文件信息添加失败");
        return null;
    }
}
