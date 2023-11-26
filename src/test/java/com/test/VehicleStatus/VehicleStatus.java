package com.test.VehicleStatus;
public class VehicleStatus {
    private String vehicleNumber;
    private String maintenanceStatus;
    private int remainingMaintenanceTime;
    private boolean hasFault;

    public VehicleStatus(String vehicleNumber, String maintenanceStatus, int remainingMaintenanceTime, boolean hasFault) {
        this.vehicleNumber = vehicleNumber;
        this.maintenanceStatus = maintenanceStatus;
        this.remainingMaintenanceTime = remainingMaintenanceTime;
        this.hasFault = hasFault;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public int getRemainingMaintenanceTime() {
        return remainingMaintenanceTime;
    }

    public boolean hasFault() {
        return hasFault;
    }
}
