package com.whut.truck.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoToRe", value = "/LoToRe")
public class LoToRe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 处理 GET 请求
//        System.out.println("LoToRe Servlet doGet method called");
        // 回到注册页面
        response.sendRedirect("Register.jsp");
    }
}