package com.whut.truck.servlet;

import com.google.gson.JsonObject;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.utils.HttpCommunicationLayer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "Fix", value = "/Fix")
public class Fix extends HttpServlet {
    private VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String id = req.getParameter("VehicleName");

        VehicleStatusDto vehicleStatusDto = this.vehicleStatusService.find(id);

        HttpCommunicationLayer communicationLayer = new HttpCommunicationLayer();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 定义日期时间格式化模式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        // 格式化日期时间
        String formattedDateTime = currentDateTime.format(formatter);

        JsonObject gson = null;
        try {
            gson = communicationLayer.connectToPython(vehicleStatusDto.getVehicleStatus().getVehicle_file().getBinaryStream(), formattedDateTime + "_" + id + ".txt", "classify");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(gson);
        String wrong = gson.get("result").getAsString();
        req.setAttribute("Wrong", wrong);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Fix.jsp");
        dispatcher.forward(req, resp);
    }
}
