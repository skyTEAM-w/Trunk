package org.whut.trunk.login;

import org.whut.trunk.Dto.SystemAdminDto;
import org.whut.trunk.Service.SystemAdminService;
import org.whut.trunk.Service.impl.SystemAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginTest", value = "/loginTest")
public class LoginTest extends HttpServlet {
    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("user_name");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h1>欢迎你</h1>");
        printWriter.println("<p>" + username + "</p>");
        printWriter.println("<p>" + password + "</p>");

        SystemAdminDto systemAdminDto = this.systemAdminService.login(username, password);
        switch (systemAdminDto.getMsg()){
            case 0:
                req.setAttribute("usernameError","用户名不存在");
                req.getRequestDispatcher("login.jsp" ).forward(req,resp);break;
            case 1:
                req.setAttribute( "passwordError","密码错误");
                req.getRequestDispatcher("login.jsp" ).forward(req,resp);break;
            case 2:
                System.out.println("登录成功! ");
                //跳转登录页面
                break;
        }
//        resp.sendRedirect("Hall.jsp");
    }
}
