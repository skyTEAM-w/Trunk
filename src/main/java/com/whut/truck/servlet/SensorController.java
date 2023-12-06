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
    protected void csv_save(InputStream inputStream) throws IOException {                //添加csv
        this.sensorService.csv_save(inputStream);
        System.out.println("插入成功");
    }

    protected void csv_find() throws IOException {         //查询csv
        System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();
        SensorDto systemAdminDto = this.sensorService.csv_find(input_id);
        //System.out.println(systemAdminDto.getSensor().getS1());
    }

    protected void delete_csv() throws IOException {           //删除csv
        /*System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/

        SensorDto systemAdminDto = this.sensorService.csv_delete(null);
        System.out.println("删除成功");
    }
}
