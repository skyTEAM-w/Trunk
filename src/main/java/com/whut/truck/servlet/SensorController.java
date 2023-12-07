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

    public void csv_find(String id) throws IOException {         //查询csv
       /* System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/
        SensorDto sensorDto = this.sensorService.csv_find(id);

        for (Sensor admin : sensorDto.getList_Sensor()) {
            System.out.println(admin.getLine() + " " + admin.getSensor_id() + " " + admin.getDescribe() + " " + admin.getSetting1() + " " + admin.getSetting2() + " " + admin.getSetting3() + " " + admin.getS1() + " " + admin.getS2() + " " + admin.getS3() + " " + admin.getS4() + " " + admin.getS5() + " " + admin.getS6() + " " + admin.getS7() + " " + admin.getS8() + " " + admin.getS9() + " " + admin.getS10() + " " + admin.getS11() + " " + admin.getS12() + " " + admin.getS13() + " " + admin.getS14() + " " + admin.getS15() + " " + admin.getS16() + " " + admin.getS17() + " " + admin.getS18() + " " + admin.getS19() + " " + admin.getS20() + " " + admin.getS21());
        }
    }

    public void delete_csv() throws IOException {           //删除全部传感器数据
        /*System.out.print("请输入需要查找的id：");
        Scanner scanner_quantity = new Scanner(System.in);
        String input_id = scanner_quantity.nextLine();*/

        SensorDto systemAdminDto = this.sensorService.csv_delete(null);
        System.out.println("删除成功");
    }
}
