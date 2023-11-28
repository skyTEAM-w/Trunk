package org.whut.trunk.Service.impl;

import org.whut.trunk.Dao.VehicleStatusDao;
import org.whut.trunk.Dao.impl.VehicleStatusDaoImpl;
import org.whut.trunk.Service.VehicleStatusService;
import org.whut.trunk.entity.VehicleStatus;
import java.util.List;

public class VehicleStatusServiceImpl implements VehicleStatusService {
    private VehicleStatusDao vehicleStatusDao = new VehicleStatusDaoImpl();

    @Override
    public List<VehicleStatus> list() {
        return this.vehicleStatusDao.list();
    }
}
