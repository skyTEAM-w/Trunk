package com.whut.truck.Service;

import com.whut.truck.Dto.VehicleStatusDto;

import java.io.IOException;

public interface VehicleStatusService {
    public VehicleStatusDto find(String vehicle_id) throws IOException;
}
