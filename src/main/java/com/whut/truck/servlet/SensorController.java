package com.whut.truck.servlet;

import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.impl.SensorServiceImpl;
import com.whut.truck.entity.Sensor;
import com.whut.truck.Service.SensorService;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SensorController {
    private SensorService sensorService = new SensorServiceImpl();
    public void csv_save(InputStream inputStream) throws IOException {                //添加csv
        this.sensorService.csv_save(inputStream);
        System.out.println("插入成功");
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

    public void delete_csv() throws IOException {           //删除全部传感器数据
        /*System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/

        SensorDto systemAdminDto = this.sensorService.csv_delete(null);
        System.out.println("删除成功");
    }
}
