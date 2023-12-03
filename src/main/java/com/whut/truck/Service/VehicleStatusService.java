package com.whut.truck.Service;

import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;

public interface VehicleStatusService {
    public VehicleStatusDto find(String vehicle_id) throws IOException;
    public VehicleStatusDto save(VehicleStatus vehicleStatus) throws IOException;
    public VehicleStatusDto Insertfile(Integer id) throws IOException;

}
