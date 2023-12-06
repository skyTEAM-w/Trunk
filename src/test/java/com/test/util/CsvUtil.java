package com.test.util;

import com.csvreader.CsvWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

public class CsvUtil {
    public static <T> void writeCSV(Collection<T> list, String  csvFilePath, String[] csvHeaders) {
        try {
            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',',  Charset.forName("UTF-8"));
            // 写表头
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            //遍历集合
            for (T t : list) {
                //获取类属性
                Field[] fields = t.getClass().getDeclaredFields();
                String[] csvContent = new String[fields.length];
                for (short i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Class<?> tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName);
                        Object value = getMethod.invoke(t);
                        if (value == null) {
                            continue;
                        }
                        //取值并赋给数组
                        String textvalue = value.toString();
                        csvContent[i] = textvalue;
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }

                //迭代插入记录
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("<--------CSV文件写入成功-------->");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
