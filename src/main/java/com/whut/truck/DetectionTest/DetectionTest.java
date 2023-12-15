package com.whut.truck.DetectionTest;

import com.google.gson.JsonObject;
import com.whut.truck.Dto.VehicleStatusDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.Service.VehicleStatusService;
import com.whut.truck.Service.impl.SystemAdminServiceImpl;
import com.whut.truck.Service.impl.VehicleStatusServiceImpl;
import com.whut.truck.entity.VehicleStatus;
import com.whut.truck.servlet.SensorController;
import com.whut.truck.servlet.VehicleStatusController;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Arrays;


@WebServlet(name = "DetectionTest", value = "/DetectionTest")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10MB
        maxFileSize = 1024 * 1024 * 20,        // 20MB
        maxRequestSize = 1024 * 1024 * 50      // 50MB
)
public class DetectionTest extends HttpServlet {

    private VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
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
            String id = null;

            String[] parts = fileName.split("_");
            if (parts.length >= 3) {
                id = parts[2].substring(0, parts[2].indexOf("."));
                //System.out.println("ID: " + id);
            } else {
                System.out.println("文件命名格式错误");
            }

            // 读取文件内容到字节数组，创建ByteArrayInputStream对象传递给connectToPython方法
            byte[] fileContent = getFileContent(filePart);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContent);

            /*System.out.println(Arrays.toString(fileContent));
            System.out.println(new String(fileContent, StandardCharsets.UTF_8));*/

            if(selectName.equals("sensor")){
                SensorController sensorController = new SensorController();
                sensorController.csv_save(inputStream);
            }else if(selectName.equals("vibration")){
                VehicleStatusService vehicleStatusService = new VehicleStatusServiceImpl();
                VehicleStatusDto vehicleStatusDto = this.vehicleStatusService.find(id);
                if(vehicleStatusDto.getMsg()==0){
                    VehicleStatus vehicleStatus = new VehicleStatus(id, "null", 1, "null", "null", "null", null);
                    vehicleStatusService.save(vehicleStatus);
                    vehicleStatusService.Insertfile(id, inputStream);
                    System.out.println("创建并插入文件成功");
                }else{
                    vehicleStatusService.Insertfile(id, inputStream);
                    System.out.println("插入文件成功");
                }
            }else{
                System.out.println("插入失败");
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
