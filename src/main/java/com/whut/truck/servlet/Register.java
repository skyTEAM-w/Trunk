package com.whut.truck.servlet;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.Service.impl.SystemAdminServiceImpl;
import com.whut.truck.entity.SystemAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    // 从前端获取用户注册使用的用户名、邮箱、密码和验证密码
    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 doGet 请求转发给 doPost 处理
        super.doGet(req, resp);
        doPost(req, resp);
    }

    // 检测密码与验证密码是否一致，将用户注册信息存入数据库
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 从请求参数中获取用户注册信息
        String username = req.getParameter("username");
        String user_email = req.getParameter("email");
        String user_pass = req.getParameter("password");
        String confirm_pass = req.getParameter("confirm_pass");

        PrintWriter out = resp.getWriter();

        // 检查用户名是否已经注册过
        SystemAdminDto systemAdminDto1 = this.systemAdminService.check(username);

        switch (systemAdminDto1.getMsg()) {
            case 0:
                // 用户名可用，可以进行注册
                req.setAttribute("username", "新用户名，可以注册");
                SystemAdminDto systemAdminDto = this.systemAdminService.save(new SystemAdmin(username, user_pass, user_email));
                if (user_pass.equals(confirm_pass)) {
                    // 密码与验证密码一致，注册成功
                    out.flush();
                    out.println("<script>alert('注册成功');window.location.href = \"Login.jsp\";</script>");
                } else {
                    // 密码与验证密码不一致，提示用户重新输入
                    out.flush();
                    out.println("<script>alert('两次密码输入不一致，请重新输入');history.back();</script>");
                }
                break;
            case 3:
                // 用户名已经被注册，提示用户重新注册
                req.setAttribute("usernameError", "用户名已被注册，请重新注册");
                out.flush();
                out.println("<script>alert('用户名已被注册，请重新注册');history.back();</script>");
                break;
        }
    }
}
