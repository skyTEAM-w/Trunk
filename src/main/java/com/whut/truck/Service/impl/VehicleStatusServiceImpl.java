package com.whut.truck.Service.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Dao.impl.VehicleStatusDaoImpl;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.util.List;

public class VehicleStatusServiceImpl implements VehicleStatusService {
    private VehicleStatusDao vehicleStatusDao = new VehicleStatusDaoImpl();

    @Override
    public List<VehicleStatus> list() throws IOException {
        return this.vehicleStatusDao.list();
    }
}
