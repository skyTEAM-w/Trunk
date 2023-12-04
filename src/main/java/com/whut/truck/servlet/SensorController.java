package com.whut.truck.servlet;

import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.impl.SensorServiceImpl;
import com.whut.truck.entity.Sensor;
import com.whut.truck.Service.SensorService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SensorController {
    private SensorService sensorService = new SensorServiceImpl();
    protected void csv_save() throws IOException {                //添加csv

        String csvFile = "D:/000001/1/train_FD001.csv";
        String line;
        String csvSplitBy = ",";
        String[] data = new String[0];

        int counter = 0; // 计数器，用于限制读取的行数
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null && counter < 31) {
                data = line.split(csvSplitBy);
                if(counter!=0) {
                    this.sensorService.csv_save(new Sensor(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15], data[16], data[17], data[18], data[19], data[20], data[21], data[22], data[23], data[24], data[25], data[26]));
                }
                counter++;
                data = new String[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
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
