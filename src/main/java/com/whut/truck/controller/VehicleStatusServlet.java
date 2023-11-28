package com.whut.truck.controller;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/vehicle")
public class VehicleStatusServlet extends HttpServlet{
    private VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        req.setAttribute("list", this.vehicleStatusService.list());
        req.getRequestDispatcher("xxx.jsp").forward(req, resp);
    }
}