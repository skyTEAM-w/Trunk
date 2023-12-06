package com.whut.truck.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class HttpCommunicationLayer {
    private String PYTHON_SERVER_URL = null;

    public HttpCommunicationLayer() {
        try {
            // 获取python_host.xml文件
            InputStream xmlInputStream = HttpCommunicationLayer.class.getClassLoader().getResourceAsStream("python_host.xml");
            // 创建DocumentBuilderFactory对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 解析xml文件
            Document document = builder.parse(xmlInputStream);

            // 获取文档的根节点
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            // 获取url、port、router属性值
            String pythonHost = getTextContent(root, "url");
            String pythonPort = getTextContent(root, "port");
            String Route = getTextContent(root, "router");

            // 拼接python服务器url
            this.PYTHON_SERVER_URL = pythonHost + pythonPort + Route;

            System.out.println(this.PYTHON_SERVER_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接到 Python 服务器，发送由 InputStream 表示的文件以及附加的元数据，并从服务器接收 JSON 响应。
     *
     * @param inputStream 表示要发送到 Python 服务器的文件的 InputStream。
     * @param fileName    要发送的文件的名称。命名为：
     *                    <p> YYYYmmDD_hhMMss_id.txt
     *                    <p> 其中 id 是车辆id
     *                    <p> 例如：20200101_000000_1.txt
     * @param fileType    预测类型。
     *                    <p>
     *                    预测性维护： "predict"
     *                    </p>
     *                    <p>
     *                    故障预测："classify"
     *                    </p>
     * @return 代表从 Python 服务器接收到的响应的 JsonObject。
     * @throws IOException 如果在从 InputStream 读取或与服务器通信时发生 I/O 错误。
     */
    public JsonObject connectToPython(InputStream inputStream, String fileName, String fileType) throws IOException {
        // 从输入流中读取字节
        byte[] bytes = null;
        if (inputStream != null) {
            try {
                bytes = inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 创建 JsonObject 以保存元数据和文件数据
        JsonObject jsonObject = new JsonObject();
        // 将文件名添加到 JsonObject
        jsonObject.addProperty("file_name", fileName);
        // 将文件类型添加到 JsonObject
        jsonObject.addProperty("file_type", fileType);
        // 将经过 Base64 编码的文件数据添加到 JsonObject
        jsonObject.addProperty("file_data", Base64.getEncoder().encodeToString(bytes));


        URL url = new URL(PYTHON_SERVER_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 将请求方式设置为 POST
        connection.setRequestMethod("POST");
        // 允许连接进行输出
        connection.setDoOutput(true);
        // 设置请求的内容类型
        connection.setRequestProperty("Content-Type", "application/json");

        // 将 JsonObject 转换为 JSON 字符串
        String jsonData = jsonObject.toString();
//        System.out.println(jsonData);

        // 将 JSON 数据发送到服务器
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        // 接收并处理服务器的响应
        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            // 读取服务器的响应
            String response = scanner.useDelimiter("\\A").next();
            System.out.println("服务器响应: " + response);
            // 将响应转换为 JsonObject
            return new Gson().fromJson(response, JsonObject.class);
        } finally {
            // 与服务器断开连接
            connection.disconnect();
        }
    }

    /**
     * 从 XML 元素中检索指定 XPath 节点的文本内容。
     *
     * @param element XML 元素，从中提取文本内容。
     * @param xpath   用于定位所需节点的 XPath 表达式。
     * @return 指定 XPath 节点的文本内容；如果未找到节点，则为空字符串。
     */
    private static String getTextContent(Element element, String xpath) {
        // 获取匹配指定 XPath 表达式的节点
        NodeList nodeList = element.getElementsByTagName(xpath);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null) {
                // 获取节点的文本内容
                return node.getTextContent().trim();
            }
        }
        return "";  // 或根据需求抛出异常
    }


}