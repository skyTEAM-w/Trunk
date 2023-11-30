//package com.whut.truck.DetectionTest;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.JsonSyntaxException;
//import com.whut.truck.utils.HttpCommunicationLayer;
//
//import java.io.IOException;
//import java.io.Serial;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//@WebServlet(name = "DetectionTest", value = "/DetectionTest")
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 10,  // 10MB
//        maxFileSize = 1024 * 1024 * 20,        // 20MB
//        maxRequestSize = 1024 * 1024 * 50      // 50MB
//)
//public class DetectionTest extends HttpServlet {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request,response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpCommunicationLayer connection = new HttpCommunicationLayer();
//        // 获取上传的文件
//        List<Part> fileParts = (List<Part>) request.getParts();
//
//        boolean uploadSuccess = true; // 标记是否所有文件上传成功
//
//        for (Part filePart : fileParts) {
//            String fileName = getFileName(filePart);
//            System.out.println(fileName);
//
//            // 假设 connectToPython 返回的 JSON 格式如 {"success": true} 或 {"success": false}
//            String jsonResult = String.valueOf(connection.connectToPython(filePart.getInputStream(), fileName, "detection"));
//
//            try {
//                // 解析 JSON 响应
//                JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();
//
//                // 检查 "success" 键是否存在且不为 null
//                if (jsonObject.has("success") && !jsonObject.get("success").isJsonNull()) {
//                    // 获取 "success" 键对应的值
//                    boolean success = jsonObject.get("success").getAsBoolean();
//
//                    // 如果有一个文件上传失败，则设置上传成功为 false
//                    if (!success) {
//                        uploadSuccess = false;
//                        break;
//                    }
//                } else {
//                    // 如果 "success" 键不存在或为 null，则视为失败
//                    uploadSuccess = false;
//                    break;
//                }
//            } catch (JsonSyntaxException e) {
//                e.printStackTrace();
//                uploadSuccess = false;
//                break;
//            }
//        }
//
//        if (uploadSuccess) {
//            // 设置属性，用于在页面上判断是否显示提交成功的消息
//            request.setAttribute("uploadSuccess", true);
//        }
//
//        // 获取当前页面的 URL
//        String url = request.getRequestURL().toString();
//
//        // 通过 JavaScript 在页面上显示上传成功的消息
//        response.getWriter().write("<script>alert('上传成功！');window.location.href='" + url + "';</script>");
//    }
//
//
//    // 从Part中提取文件名
//    private String getFileName(final Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//        for (String content : partHeader.split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
//
//
//}

        package com.whut.truck.DetectionTest;

        import com.google.gson.JsonObject;
        import com.google.gson.JsonParser;
        import com.google.gson.JsonSyntaxException;
        import com.whut.truck.utils.HttpCommunicationLayer;

        import java.io.*;
        import java.util.List;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.MultipartConfig;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.Part;

@WebServlet(name = "DetectionTest", value = "/DetectionTest")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10MB
        maxFileSize = 1024 * 1024 * 20,        // 20MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
public class DetectionTest extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

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

            if (!connection.connectToPython(inputStream, fileName, "detection").has("success")
                    || connection.connectToPython(inputStream, fileName, "detection").get("success").isJsonNull()) {
                uploadSuccess = false;
                break;
            }
        }

        // 设置属性，用于在页面上判断是否显示提交成功的消息
        request.setAttribute("uploadSuccess", uploadSuccess);
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
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }
}


