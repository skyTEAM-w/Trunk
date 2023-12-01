package com.whut.truck.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("Logout");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("systemAdmin");
            session.invalidate();
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("Login.jsp");
    }
}
