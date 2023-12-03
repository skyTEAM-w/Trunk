package com.whut.truck.Dao;

import com.whut.truck.entity.VehicleStatus;

import java.io.IOException;
import java.util.List;

public interface VehicleStatusDao {
    //public List<VehicleStatus> list() throws IOException;
    public VehicleStatus findByVehicle_id(String vehicle_id) throws IOException;
    public Integer save(VehicleStatus vehicleStatus) throws IOException;
    public Integer Insert_file(Integer id) throws IOException;
}
