package com.whut.truck.DetectionTest;

import java.io.IOException;
import java.io.Serial;
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
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,        // 10MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
public class DetectionTest extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取上传的文件
        List<Part> fileParts = (List<Part>) request.getParts();

        for (Part filePart : fileParts) {
            String fileName = getFileName(filePart);
            System.out.println(fileName);

            // 将文件保存到服务器指定目录
            filePart.write("\\resources\\" + fileName);
        }

        // 处理完文件上传后，可以进行其他操作，比如数据库操作等
        // ...

        // 重定向到其他页面或返回响应
        response.sendRedirect("success.jsp");
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


}
