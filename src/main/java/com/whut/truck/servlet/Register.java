package com.whut.truck.servlet;

import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.Service.impl.SystemAdminServiceImpl;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.Dto.SystemAdminDto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    //从前端获取用户注册使用的用户名、邮箱、密码和验证密码
    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    //检测密码与验证密码是否一致，将用户注册信息存入数据库
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String user_email = req.getParameter("email");
        String user_pass = req.getParameter("password");
        String confirm_pass = req.getParameter("confirm_pass");

        PrintWriter out = resp.getWriter();

        SystemAdminDto systemAdminDto1 = this.systemAdminService.check(username);


        switch (systemAdminDto1.getMsg()) {
            case 0:
                req.setAttribute("username", "新用户名，可以注册");
                SystemAdminDto systemAdminDto = this.systemAdminService.save(new SystemAdmin(username, user_pass, user_email));
                if (user_pass.equals(confirm_pass)) {
                    out.flush();
                    out.println("<script>alert('注册成功');window.location.href = \"Login.jsp\";</script>");
                    /*req.getRequestDispatcher("Login.jsp").forward(req, resp);*/
                } else {
                    out.flush();
                    out.println("<script>alert('两次密码输入不一致，请重新输入');history.back();</script>");
                    /*req.getRequestDispatcher("Register.jsp").forward(req, resp);*/
                }
                //req.getRequestDispatcher("Register.jsp").forward(req, resp);
                break;
            case 3:
                req.setAttribute("usernameError", "用户名已被注册，请重新注册");
                out.flush();
                out.println("<script>alert('用户名已被注册，请重新注册');history.back();</script>");
                /*req.getRequestDispatcher("Register.jsp").forward(req, resp);*/
                break;
        }
    }
}
