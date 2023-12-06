package com.whut.truck.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HallServlet", urlPatterns = "/HallServlet")
public class HallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String component = req.getParameter("componentName");
        System.out.println(component);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/" + component + ".jsp");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        dispatcher.forward(req, resp);
    }
}
