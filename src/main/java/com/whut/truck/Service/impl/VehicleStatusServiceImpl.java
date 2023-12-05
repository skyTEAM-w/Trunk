package com.whut.truck.Service.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Dao.impl.VehicleStatusDaoImpl;
import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.entity.VehicleStatus;
import java.io.IOException;
import java.io.InputStream;

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

    @Override
    public VehicleStatusDto save(VehicleStatus vehicleStatus) throws IOException {
        Integer save = this.vehicleStatusDao.save(vehicleStatus);
        if(save != 1) throw new RuntimeException("车辆信息添加失败");
        return null;
    }

    @Override
    public VehicleStatusDto Insertfile(Integer id, InputStream inputStream) throws IOException {
        Integer Insert = this.vehicleStatusDao.Insert_file(id, inputStream);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if(Insert != 1) throw new RuntimeException("文件信息添加失败");
        return null;
    }

}
