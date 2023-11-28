package com.whut.truck.Service;

import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.util.List;

public interface VehicleStatusService {
    public List<VehicleStatus> list() throws IOException;
}
