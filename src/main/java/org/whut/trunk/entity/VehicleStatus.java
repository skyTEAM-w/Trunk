package org.whut.trunk.entity;

public class VehicleStatus {
    private Integer vehicle_id ;
    private String maintenance_status;
    private String estimated_maintenance_time ;
    private String previous_failure_status;

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getMaintenance_status() {
        return maintenance_status;
    }

    public void setMaintenance_status(String maintenance_status) {
        this.maintenance_status = maintenance_status;
    }

    public String getEstimated_maintenance_time() {
        return estimated_maintenance_time;
    }

    public void setEstimated_maintenance_time(String estimated_maintenance_time) {
        this.estimated_maintenance_time = estimated_maintenance_time;
    }

    public String getPrevious_failure_status() {
        return previous_failure_status;
    }

    public void setPrevious_failure_status(String previous_failure_status) {
        this.previous_failure_status = previous_failure_status;
    }

    public VehicleStatus(Integer vehicle_id, String maintenance_status, String estimated_maintenance_time, String previous_failure_status) {
        this.vehicle_id = vehicle_id;
        this.maintenance_status = maintenance_status;
        this.estimated_maintenance_time = estimated_maintenance_time;
        this.previous_failure_status = previous_failure_status;
    }
}
