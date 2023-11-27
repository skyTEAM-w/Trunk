package org.whut.trunk.Register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

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

        if (user_pass.equals(confirm_pass)){
            out.println("<h1>成功获取到用户信息如下：</h1>");
            out.println("用户名："+username+"<br>");
            out.println("邮箱："+user_email+"<br>");
            out.println("密码："+user_pass+"<br>");
        }
        else {
            out.flush();
            out.println("<script>alert('两次密码输入不一致，请重新输入');history.back();</script>");
        }
    }
}        //获取前端数据
