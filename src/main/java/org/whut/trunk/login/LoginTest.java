package org.whut.trunk.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginTest", value = "/loginTest")
public class LoginTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("user_name");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h1>欢迎你</h1>");
        printWriter.println("<p>" + username + "</p>");
        printWriter.println("<p>" + password + "</p>");
        resp.sendRedirect("Hall.jsp");

        //返回密码错误或用户名不存在
//        printWriter.flush();
//        printWriter.println("<script>alert('用户名或密码错误');history.back();</script>");
    }
}
