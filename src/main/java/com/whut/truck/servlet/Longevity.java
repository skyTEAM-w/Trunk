package com.whut.truck.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.SensorServiceImpl;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.utils.HttpCommunicationLayer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/Longevity", name = "Longevity")
public class Longevity extends HttpServlet {
    private SensorService sensorService = new SensorServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String id = req.getParameter("VehicleName");
        SensorDto sensorDto = this.sensorService.csv_find(id);
        HttpCommunicationLayer communicationLayer = new HttpCommunicationLayer();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 定义日期时间格式化模式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        // 格式化日期时间
        String formattedDateTime = currentDateTime.format(formatter);

        JsonObject gson = communicationLayer.connectToPython(sensorDto.getSensorStream(), formattedDateTime + "_" + id + ".txt", "predict");
        System.out.println(gson);
        String Longevity = gson.get("result").getAsString();
        req.setAttribute("Longevity", Longevity);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Longevity.jsp");
        dispatcher.forward(req, resp);
    }
}
