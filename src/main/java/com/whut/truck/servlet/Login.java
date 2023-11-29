package com.whut.truck.servlet;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.Service.impl.SystemAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();
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
        PrintWriter out = resp.getWriter();
        /*printWriter.write("<h1>你好</h1>");
        printWriter.println("<p>" + username + "</p>");
        printWriter.println("<p>" + password + "</p>");*/

        SystemAdminDto systemAdminDto = this.systemAdminService.login(username, password);
        switch (systemAdminDto.getMsg()) {
            case 0 -> {
                req.setAttribute("usernameError", "用户名不存在");
                out.flush();
                out.println("<script>alert('用戶名不存在，请重新输入');history.back();</script>");
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
            case 1 -> {
                req.setAttribute("passwordError", "密码错误");
                out.flush();
                out.println("<script>alert('密码错误，请重新输入');history.back();</script>");
            }
            /*req.getRequestDispatcher("Login.jsp" ).forward(req,resp);*/
            case 2 -> {
                System.out.println("登录成功! ");
                //跳转登录页面
                PrintWriter printWriter = resp.getWriter();
                printWriter.write("<h1>你好</h1>");
                printWriter.println("<p>" + username + "</p>");
                printWriter.println("<p>" + password + "</p>");
                req.getSession().setAttribute("systemAdmin", systemAdminDto);
                resp.sendRedirect("Hall.jsp");
            }
        }
//        resp.sendRedirect("Hall.jsp");
    }
}
