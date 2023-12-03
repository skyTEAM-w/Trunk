package com.whut.truck.entity;

import java.sql.Blob;

public class VehicleStatus {
    private String vehicle_id ;
    private String maintenance_status;
    private Integer estimated_maintenance_time ;
    private String previous_failure_status;

    private String Last_Maintenance_date;

    private String Maintenance_Frequency;

    private Blob Vehicle_file;

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getMaintenance_status() {
        return maintenance_status;
    }

    public void setMaintenance_status(String maintenance_status) {
        this.maintenance_status = maintenance_status;
    }

    public Integer getEstimated_maintenance_time() {
        return estimated_maintenance_time;
    }

    public void setEstimated_maintenance_time(Integer estimated_maintenance_time) {
        this.estimated_maintenance_time = estimated_maintenance_time;
    }

    public String getPrevious_failure_status() {
        return previous_failure_status;
    }

    public void setPrevious_failure_status(String previous_failure_status) {
        this.previous_failure_status = previous_failure_status;
    }

    public String getLast_Maintenance_date() {
        return Last_Maintenance_date;
    }

    public void setLast_Maintenance_date(String last_Maintenance_date) {
        Last_Maintenance_date = last_Maintenance_date;
    }

    public String getMaintenance_Frequency() {
        return Maintenance_Frequency;
    }

    public void setMaintenance_Frequency(String maintenance_Frequency) {
        Maintenance_Frequency = maintenance_Frequency;
    }

    public Blob getVehicle_file() {
        return Vehicle_file;
    }

    public void setVehicle_file(Blob vehicle_file) {
        Vehicle_file = vehicle_file;
    }

    public VehicleStatus(String vehicle_id, String maintenance_status, Integer estimated_maintenance_time, String previous_failure_status, String last_Maintenance_date, String maintenance_Frequency, Blob vehicle_file) {
        this.vehicle_id = vehicle_id;
        this.maintenance_status = maintenance_status;
        this.estimated_maintenance_time = estimated_maintenance_time;
        this.previous_failure_status = previous_failure_status;
        Last_Maintenance_date = last_Maintenance_date;
        Maintenance_Frequency = maintenance_Frequency;
        Vehicle_file = vehicle_file;
    }
}
