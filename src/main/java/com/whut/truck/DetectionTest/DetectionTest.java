package com.whut.truck.DetectionTest;

import com.google.gson.JsonObject;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetectionTest", value = "/DetectionTest")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10MB
        maxFileSize = 1024 * 1024 * 20,        // 20MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
public class DetectionTest extends HttpServlet {

    /**
     * 处理POST请求，接收上传的文件并调用后台处理方法。
     *
     * @param request  HttpServletRequest对象，包含客户端请求的信息
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @throws ServletException 如果发生Servlet异常
     * @throws IOException      如果发生IO异常
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectName = request.getParameter("selectName");
        System.out.println(selectName);

        //TODO: 实现根据选择类型调用不同的数据库连接方法将文件信息载入数据库

        // 创建HttpCommunicationLayer对象，用于与后台通信
        HttpCommunicationLayer connection = new HttpCommunicationLayer();

        // 获取上传的文件
        List<Part> fileParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals("upload")) {
                fileParts.add(part);
            }
        }

        // 标记是否所有文件上传成功
        boolean uploadSuccess = true;

        // 遍历上传的文件列表
        for (Part filePart : fileParts) {
            // 获取文件名
            String fileName = getFileName(filePart);
            System.out.println(fileName);

            // 读取文件内容到字节数组，创建ByteArrayInputStream对象传递给connectToPython方法
            byte[] fileContent = getFileContent(filePart);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent);

            // 通过connectToPython方法获取后台处理结果
            JsonObject result = connection.connectToPython(inputStream, fileName, "detection");

            // 检查处理结果中是否包含成功标志
            if (result.has("success") && !result.get("success").isJsonNull()) {
                boolean success = result.get("success").getAsBoolean();

                // 如果有一个文件上传失败，则设置uploadSuccess为false
                if (!success) {
                    uploadSuccess = false;
                    break;
                }
            } else {
                // 如果处理结果中不存在成功标志，则视为失败
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

    /**
     * 从Part中提取文件名。
     *
     * @param part 包含文件信息的Part对象
     * @return 文件名
     */
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println(partHeader);

        // 检查是否为文件上传的部分
        if (part.getName().equals("upload")) {
            for (String content : partHeader.split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }

        return null;
    }


    /**
     * 读取文件内容到字节数组。
     *
     * @param filePart 包含文件信息的Part对象
     * @return 包含文件内容的字节数组
     * @throws IOException 如果发生IO异常
     */
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
