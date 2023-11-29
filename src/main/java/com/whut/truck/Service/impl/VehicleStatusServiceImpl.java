package com.whut.truck.Service.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Dao.impl.VehicleStatusDaoImpl;
import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.entity.VehicleStatus;
import java.io.IOException;

public class VehicleStatusServiceImpl implements VehicleStatusService {
    private VehicleStatusDao vehicleStatusDao = new VehicleStatusDaoImpl();
    @Override
    public VehicleStatusDto find(String vehicle_id) throws IOException {             //通过vehicle_id进行查询
        VehicleStatus vehicleStatus = this.vehicleStatusDao.findByVehicle_id(vehicle_id);
        VehicleStatusDto vehicleStatusDto = new VehicleStatusDto();

        if (vehicleStatus == null) {
            vehicleStatusDto.setMsg(0);           //0表示车辆id不存在
        } else {
            vehicleStatusDto.setMsg(1);            //1表示可以查询
            vehicleStatusDto.setVehicleStatus(vehicleStatus);
        }
        return vehicleStatusDto;
    }
}
