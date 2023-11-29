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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class HttpCommunicationLayer {
    private String PYTHON_SERVER_URL = null;

    public HttpCommunicationLayer() {
        try {
            InputStream xmlInputStream = HttpCommunicationLayer.class.getClassLoader().getResourceAsStream("python_host.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlInputStream);

            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            String pythonHost = getTextContent(root, "url");
            String pythonPort = getTextContent(root, "port");
            String Route = getTextContent(root, "router");

            this.PYTHON_SERVER_URL = pythonHost + pythonPort + Route;

            System.out.println(this.PYTHON_SERVER_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jsonObject.addProperty("file_data", Base64.getEncoder().encodeToString(bytes));

        URL url = new URL(PYTHON_SERVER_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        String jsonData = jsonObject.toString();
        System.out.println(jsonData);

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
            String response = scanner.useDelimiter("\\A").next();
            System.out.println("Server Response: " + response);
            return new Gson().fromJson(response, JsonObject.class);
        } finally {
            connection.disconnect();
        }

    }

    private static String getTextContent(Element element, String xpath) {
        NodeList nodeList = element.getElementsByTagName(xpath);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null) {
                return node.getTextContent().trim();
            }
        }
        return "";  // 或者抛出异常，具体取决于你的需求
    }


}
