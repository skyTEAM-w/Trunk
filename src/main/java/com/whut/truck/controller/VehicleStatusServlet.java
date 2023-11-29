package com.whut.truck.controller;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.VehicleStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "VehicleStatusServlet" , value="/VehicleStatusServlet")
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
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String vehicle_id = req.getParameter("VehicleName");
        System.out.println(vehicle_id);
        VehicleStatusDto vehicleStatusDto = this.vehicleStatusService.find(vehicle_id);
        PrintWriter out = resp.getWriter();

        if(vehicleStatusDto.getMsg()==0){
           // req.setAttribute("vehicle_idError","车辆编号不存在");
            out.flush();
            out.println("<script>alert('车辆编号不存在，请重新输入');</script>");
            req.getRequestDispatcher("VehicleStatus.jsp" ).forward(req,resp);
        }else if(vehicleStatusDto.getMsg()==1){
            out.flush();
            out.println("<script>alert('查询成功');</script>");
            //req.getRequestDispatcher("xxx.jsp").forward(req, resp);
        }
    }
}