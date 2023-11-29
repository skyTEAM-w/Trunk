package com.test.pytorch;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;

public class jsonHttpTest {
    @Test
    public void test() throws IOException {
        try {
            HttpURLConnection connection = getHttpURLConnection();
            try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
                String response = scanner.useDelimiter("\\A").next();
                System.out.println("Server Response: " + response);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static HttpURLConnection getHttpURLConnection() throws IOException {
        // 创建一个URL对象，指定要连接的地址
        URL url = new URL("http://localhost:19911/predict");
        // 打开一个HttpURLConnection连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置请求方式
        connection.setRequestMethod("POST");
        // 设置可以输出
        connection.setDoOutput(true);

        // 设置请求头
        connection.setRequestProperty("Content-Type", "application/json");

        // 获取文件输入流
        InputStream fileStream = jsonHttpTest.class.getClassLoader().getResourceAsStream("jsonTest.txt");
        byte[] fileBytes = null;
        if (fileStream != null) {
            fileBytes = fileStream.readAllBytes();
        }

        // 将文件字节转换为JsonObject
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("file_data", Base64.getEncoder().encodeToString(fileBytes));

        // 将JsonObject转换为字符串
        String jsonData = jsonObject.toString();

        // 将字符串写入输出流
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return connection;
    }
}
