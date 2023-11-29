package com.whut.truck.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于加载非受保护页面的类。
 */
public class unprotectedPagesLoader {

    /**
     * 获取非受保护页面的路径列表。
     *
     * @param contextPath 上下文路径，用于拼接页面路径。
     * @return 非受保护页面的完整路径列表。
     */
    public static List<String> getUnprotectedPages(String contextPath) {
        // 用于存储非受保护页面路径的列表
        List<String> unprotectedPages = new ArrayList<>();

        try {
            // 从类路径中获取 "excluded_pages.xml" 文件的输入流
            InputStream inputStream = unprotectedPagesLoader.class.getClassLoader().getResourceAsStream("excluded_pages.xml");

            // 使用 DocumentBuilder 解析 XML 文件
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // 获取所有 <page> 元素的节点列表
            NodeList nodeList = document.getElementsByTagName("page");

            // 遍历 <page> 元素，提取页面路径，并将完整路径添加到列表中
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element pageElement = (Element) nodeList.item(i);
                // 获取 <page> 元素的文本内容，即页面路径
                String pagePath = pageElement.getTextContent();
                unprotectedPages.add(contextPath + pagePath);
            }
        } catch (Exception e) {
            // 处理异常，打印堆栈轨迹
            e.printStackTrace();
        }

        // 返回非受保护页面的完整路径列表
        return unprotectedPages;
    }
}

