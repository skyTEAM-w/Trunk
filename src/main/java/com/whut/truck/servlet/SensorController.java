package com.whut.truck.servlet;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.Dao.impl.VehicleStatusDaoImpl;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.impl.SensorServiceImpl;
import com.whut.truck.entity.Sensor;
import com.whut.truck.Service.SensorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
@WebServlet(name = "SensorController", value = "/SensorController")
public class SensorController extends HttpServlet {
    private SensorService sensorService = new SensorServiceImpl();
    public void csv_save(InputStream inputStream) throws IOException {                //添加csv
        this.sensorService.csv_save(inputStream);
        System.out.println("插入成功");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求参数中获取车辆编号
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String VehicleID = request.getParameter("VehicleName");
        String cycle = request.getParameter("cycle");
        String[] data = new String[27];
        System.arraycopy(output_one_lineBY_id(VehicleID, cycle),0,data,0,27);

        String result = data[0] + "\n";
        request.setAttribute("Data0", result);
        result = data[1] + "\n";
        request.setAttribute("Data1", result);
        result = data[2] + "\n";
        request.setAttribute("Data2", result);
        result = data[3] + "\n";
        request.setAttribute("Data3", result);
        result = data[4] + "\n";
        request.setAttribute("Data4", result);
        result = data[5] + "\n";
        request.setAttribute("Data5", result);
        result = data[6] + "\n";
        request.setAttribute("Data6", result);
        result = data[7] + "\n";
        request.setAttribute("Data7", result);
        result = data[8] + "\n";
        request.setAttribute("Data8", result);
        result = data[9] + "\n";
        request.setAttribute("Data9", result);
        result = data[10] + "\n";
        request.setAttribute("Data10", result);
        result = data[11] + "\n";
        request.setAttribute("Data11", result);
        result = data[12] + "\n";
        request.setAttribute("Data12", result);
        result = data[13] + "\n";
        request.setAttribute("Data13", result);
        result = data[14] + "\n";
        request.setAttribute("Data14", result);
        result = data[15] + "\n";
        request.setAttribute("Data15", result);
        result = data[16] + "\n";
        request.setAttribute("Data16", result);
        result = data[17] + "\n";
        request.setAttribute("Data17", result);
        result = data[18] + "\n";
        request.setAttribute("Data18", result);
        result = data[19] + "\n";
        request.setAttribute("Data19", result);
        result = data[20] + "\n";
        request.setAttribute("Data20", result);
        result = data[21] + "\n";
        request.setAttribute("Data21", result);
        result = data[22] + "\n";
        request.setAttribute("Data22", result);
        result = data[23] + "\n";
        request.setAttribute("Data23", result);
        result = data[24] + "\n";
        request.setAttribute("Data24", result);
        result = data[25] + "\n";
        request.setAttribute("Data25", result);
        result = data[26] + "\n";
        request.setAttribute("Data26", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VehicleSensor.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 按车辆编号查询传感器数据，以CSVStream形式输出
     * @param id 需要查询的车辆编号
     * @return CSV输入流
     * @throws IOException
     */
    public InputStream csv_find(String id) throws IOException {         //查询csv
       /* System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/
        SensorDto sensorDto = this.sensorService.csv_find(id);
        return sensorDto.getSensorStream();
    }

    /**
     * @return null
     * @throws IOException
     */
    public void delete_csv() throws IOException {           //删除全部传感器数据
        /*System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/

        SensorDto systemAdminDto = this.sensorService.csv_delete(null);
        System.out.println("删除成功");
    }

    /**
     *
     * @param id
     * @return data字符组
     * @throws IOException
     */
    public String[] output_one_lineBY_id(String id,String cycle) throws IOException {
        SensorDto sensorDto = this.sensorService.csv_output_one_line(id, cycle);
        String[] data = new String[27];
        data[0] = sensorDto.getSensor().getLine();
        data[1] = sensorDto.getSensor().getSensor_id();
        data[2] = sensorDto.getSensor().getDescribe();
        data[3] = sensorDto.getSensor().getSetting1();
        data[4] = sensorDto.getSensor().getSetting2();
        data[5] = sensorDto.getSensor().getSetting3();
        data[6] = sensorDto.getSensor().getS1();
        data[7] = sensorDto.getSensor().getS2();
        data[8] = sensorDto.getSensor().getS3();
        data[9] = sensorDto.getSensor().getS4();
        data[10] = sensorDto.getSensor().getS5();
        data[11] = sensorDto.getSensor().getS6();
        data[12] = sensorDto.getSensor().getS7();
        data[13] = sensorDto.getSensor().getS8();
        data[14] = sensorDto.getSensor().getS9();
        data[15] = sensorDto.getSensor().getS10();
        data[16] = sensorDto.getSensor().getS11();
        data[17] = sensorDto.getSensor().getS12();
        data[18] = sensorDto.getSensor().getS13();
        data[19] = sensorDto.getSensor().getS14();
        data[20] = sensorDto.getSensor().getS15();
        data[21] = sensorDto.getSensor().getS16();
        data[22] = sensorDto.getSensor().getS17();
        data[23] = sensorDto.getSensor().getS18();
        data[24] = sensorDto.getSensor().getS19();
        data[25] = sensorDto.getSensor().getS20();
        data[26] = sensorDto.getSensor().getS21();
        System.out.println("查询成功");
        return data;
    }
}
