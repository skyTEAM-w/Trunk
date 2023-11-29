package com.test.util;

import com.whut.truck.Util.unprotectedPagesLoader;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class pagesLoader {
    @Test
    public void main() {
        System.out.println(getUnprotectedPages());
    }
    public static List<String> getUnprotectedPages() {
        List<String>  unprotectedPages = new ArrayList<>();
        try {
            InputStream inputStream = unprotectedPagesLoader.class.getClassLoader().getResourceAsStream("excluded_pages.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            NodeList nodeList = document.getElementsByTagName("page");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element pageElement = (Element) nodeList.item(i);
                // 获取 <page> 元素的文本内容，即页面路径
                String pagePath = pageElement.getTextContent();
                unprotectedPages.add(pagePath);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return unprotectedPages;
    }
}
