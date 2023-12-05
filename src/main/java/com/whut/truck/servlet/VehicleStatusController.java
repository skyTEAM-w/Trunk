package com.whut.truck.servlet;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.Dto.VehicleStatusDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebServlet(name = "VehicleStatusController", value = "/VehicleStatusController")
public class VehicleStatusController extends HttpServlet {
    private VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求参数中获取车辆编号
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String VehicleID = request.getParameter("VehicleName");
        VehicleStatusDto vehicleStatusDto = this.vehicleStatusService.find(VehicleID);

        switch (vehicleStatusDto.getMsg()) {
            case 0 -> {                                     //0表示车辆编号不存在
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>alert('车辆编号不存在，请重新输入');history.back();</script>");
            }
            case 1 -> {                                     //1表示有此车辆编号，可以查询
                String Maintenance_status = vehicleStatusDto.getVehicleStatus().getMaintenance_status();
                int Maintenance_time = vehicleStatusDto.getVehicleStatus().getEstimated_maintenance_time();
                String Failure_status = vehicleStatusDto.getVehicleStatus().getPrevious_failure_status();
                String Last_Maintenance_date = vehicleStatusDto.getVehicleStatus().getLast_Maintenance_date();
                String Maintenance_Frequency = vehicleStatusDto.getVehicleStatus().getMaintenance_Frequency();
                Blob Vehicle_file = vehicleStatusDto.getVehicleStatus().getVehicle_file();

                String result = Maintenance_status + "\n";
                request.setAttribute("Data1",result);
                result = Maintenance_time + "\n";
                request.setAttribute("Data2",result);
                result = Failure_status + "\n";
                request.setAttribute("Data3",result);
                result = Last_Maintenance_date + "\n";
                request.setAttribute("Data4",result);
                result = Maintenance_Frequency;
                request.setAttribute("Data5",result);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/VehicleStatus.jsp");
                dispatcher.forward(request,response);

            }
        }
    }
    private String convertDataToJson(List<Object> data) {
        //将数据转换为json
        StringBuilder jsonData = new StringBuilder("[");
        for (Object item : data) {
            jsonData.append("\"").append(item).append("\",");
        }
        if (!data.isEmpty()) {
            jsonData.deleteCharAt(jsonData.length() - 1);
        }
        jsonData.append("]");

        return jsonData.toString();
    }
}
