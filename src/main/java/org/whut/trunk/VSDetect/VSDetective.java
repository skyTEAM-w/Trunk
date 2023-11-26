package org.whut.trunk.VSDetect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
public class VSDetective extends  HttpServlet {

    private String vehicleNumber;
    private String maintenanceStatus;
    private int remainingMaintenanceTime;
    private boolean hasFault;

    public VSDetective(String vehicleNumber, String maintenanceStatus, int remainingMaintenanceTime, boolean hasFault) {
        this.vehicleNumber = vehicleNumber;
        this.maintenanceStatus = maintenanceStatus;
        this.remainingMaintenanceTime = remainingMaintenanceTime;
        this.hasFault = hasFault;
    }

    public VSDetective() {
//留空
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
