package com.whut.truck.Dao;

import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.util.List;

public interface VehicleStatusDao {
    public List<VehicleStatus> list() throws IOException;
}
