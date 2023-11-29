package com.whut.truck.servlet;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.SystemAdminServiceImpl;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.Dto.VehicleStatusDto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VehicleStatusController", value = "/VehicleStatusController")
public class VehicleStatusController extends HttpServlet {
    private VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求参数中获取车辆编号
        super.doGet(request, response);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String VehicleID = request.getParameter("VehicleName");
        VehicleStatusDto vehicleStatusDto = this.vehicleStatusService.find(VehicleID);
        switch (vehicleStatusDto.getMsg()) {
            case 0 -> {                                     //0表示车辆编号不存在
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>alert('车辆编号不存在，请重新输入');history.back();</script>");break;
            }
            case 1 -> {                                     //1表示有此车辆编号，可以查询
                PrintWriter printWriter = response.getWriter();
                printWriter.println("<p>" + "汽车编号为" + VehicleID + "</p>");break;
            }
        }
    }
}
