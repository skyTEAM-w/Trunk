package com.whut.truck.controller;

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


@WebServlet(name = "loginTest", value = "/loginTest")
public class AccountServlet extends HttpServlet {
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
        /*resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("user_name");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h1>欢迎你</h1>");
        printWriter.println("<p>" + username + "</p>");
        printWriter.println("<p>" + password + "</p>");*/

        String mode = req.getParameter("mode");                  //mode选择登录或注册
        switch (mode){
            case "login":
                String username = req.getParameter("user_name");
                String password = req.getParameter("password");
                SystemAdminDto systemAdminDto = this.systemAdminService.login(username, password);
                switch (systemAdminDto.getMsg()){
                    case 0:
                        req.setAttribute("usernameError","用户名不存在");
                        req.getRequestDispatcher("login.jsp" ).forward(req,resp);break;
                    case 1:
                        req.setAttribute( "passwordError","密码错误");
                        req.getRequestDispatcher("login.jsp" ).forward(req,resp);break;
                    case 2:
                        System.out.println("登录成功! ");break;
                        //跳转登录页面
                }break;
            case "register":
                String username_register = req.getParameter("user_name");
                String password_register = req.getParameter("password");
                String email_register = req.getParameter("email");
                this.systemAdminService.save(new SystemAdmin(username_register, password_register, email_register));
                resp.sendRedirect("login.jsp");break;
        }
    }
}