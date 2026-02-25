package com.whut.truck.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.whut.truck.config.PythonConfig;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

@Component
public class HttpCommunicationLayer {

    private final PythonConfig pythonConfig;

    public HttpCommunicationLayer(PythonConfig pythonConfig) {
        this.pythonConfig = pythonConfig;
    }

    public JsonObject connectToPython(InputStream inputStream, String fileName, String fileType) throws IOException {
        byte[] bytes = null;
        if (inputStream != null) {
            try {
                bytes = inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("file_name", fileName);
        jsonObject.addProperty("file_type", fileType);
        if (bytes != null) {
            jsonObject.addProperty("file_data", Base64.getEncoder().encodeToString(bytes));
        }

        URL url = new URL(pythonConfig.getFullUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String jsonData = jsonObject.toString();

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            String response = scanner.useDelimiter("\\A").next();
            System.out.println("服务器响应: " + response);
            return new Gson().fromJson(response, JsonObject.class);
        } finally {
            connection.disconnect();
        }
    }
}
