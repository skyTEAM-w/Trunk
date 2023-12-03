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
        // 将 doGet 请求转发给 doPost 处理
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的字符编码及内容类型
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 获取用户名和密码参数
        String username = req.getParameter("user_name");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();

        // 尝试用户登录
        SystemAdminDto systemAdminDto = this.systemAdminService.login(username, password);

        // 根据登录结果进行处理
        switch (systemAdminDto.getMsg()) {
            case 0 -> {
                // 用户名不存在，设置错误信息并返回登录页面
                req.setAttribute("usernameError", "用户名不存在");
                out.flush();
                out.println("<script>alert('用戶名不存在，请重新输入');history.back();</script>");
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
            case 1 -> {
                // 密码错误，设置错误信息并返回登录页面
                req.setAttribute("passwordError", "密码错误");
                out.flush();
                out.println("<script>alert('密码错误，请重新输入');history.back();</script>");
            }
            case 2 -> {
                // 登录成功，将用户信息存储到 Session 中，并重定向到 Hall.jsp 页面
                System.out.println("登录成功! ");
                PrintWriter printWriter = resp.getWriter();
                printWriter.write("<h1>你好</h1>");
                printWriter.println("<p>" + username + "</p>");
                printWriter.println("<p>" + password + "</p>");
                req.getSession().setAttribute("systemAdmin", username);
                resp.sendRedirect("Hall.jsp");
            }
        }
    }
}
