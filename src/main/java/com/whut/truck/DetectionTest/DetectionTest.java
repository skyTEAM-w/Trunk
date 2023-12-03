package com.whut.truck.DetectionTest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.whut.truck.utils.HttpCommunicationLayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "DetectionTest", value = "/DetectionTest")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10MB
        maxFileSize = 1024 * 1024 * 20,        // 20MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
public class DetectionTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpCommunicationLayer connection = new HttpCommunicationLayer();
        // 获取上传的文件
        List<Part> fileParts = (List<Part>) request.getParts();

        boolean uploadSuccess = true; // 标记是否所有文件上传成功

        for (Part filePart : fileParts) {
            String fileName = getFileName(filePart);
            System.out.println(fileName);

            // 修改这里，将文件内容读取到字节数组中，然后创建一个ByteArrayInputStream对象传递给connectToPython方法
            byte[] fileContent = getFileContent(filePart);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent);

            // 通过 connectToPython 方法获取后台处理结果
            JsonObject result = connection.connectToPython(inputStream, fileName, "detection");

            if (result.has("success") && !result.get("success").isJsonNull()) {
                boolean success = result.get("success").getAsBoolean();

                if (!success) {
                    uploadSuccess = false;
                    break;
                }
            } else {
                uploadSuccess = false;
                break;
            }
        }


        // 构造响应JSON
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("uploadSuccess", uploadSuccess);

        // 设置响应的内容类型和字符编码
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 返回JSON响应给前端
        response.getWriter().write(jsonResponse.toString());
    }

    // 从Part中提取文件名
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // 读取文件内容到字节数组
    private byte[] getFileContent(Part filePart) throws IOException {
        try (InputStream inputStream = filePart.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            int totalBytes = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }

            byte[] fileContent = new byte[totalBytes];
            try (InputStream contentStream = filePart.getInputStream()) {
                contentStream.read(fileContent);
            }

            return fileContent;
        }
    }
}
