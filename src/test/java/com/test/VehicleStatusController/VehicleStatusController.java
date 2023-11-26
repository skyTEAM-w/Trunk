package com.test.VehicleStatusController;

import com.test.VehicleStatus.VehicleStatus;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/getVehicleStatus")
public class VehicleStatusController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从请求参数中获取车辆编号
        String vehicleNumber = request.getParameter("vehicleNumber");

        // 模拟从数据库或其他数据源获取车辆状态
        VehicleStatus status = getVehicleStatusFromDatabase(vehicleNumber);

        // 将状态信息以JSON格式返回给前端
        response.setContentType("application/json");
        response.getWriter().write(statusToJson(status));
    }

    // 从数据库获取车辆状态的方法
    private VehicleStatus getVehicleStatusFromDatabase(String vehicleNumber) {
        // 在实际应用中，这里应该是与数据库交互的代码
        // 这里只是一个示例，返回静态数据
        return new VehicleStatus(vehicleNumber, "正常", 20, false);
    }

    // 将车辆状态转换为JSON字符串的方法
    private String statusToJson(VehicleStatus status) {
        // 在实际应用中，可以使用JSON库如Jackson或Gson来更方便地处理JSON
        return String.format("{\"vehicleNumber\":\"%s\",\"maintenanceStatus\":\"%s\",\"remainingMaintenanceTime\":%d,\"hasFault\":%s}",
                status.getVehicleNumber(), status.getMaintenanceStatus(), status.getRemainingMaintenanceTime(), status.hasFault());
    }
}
