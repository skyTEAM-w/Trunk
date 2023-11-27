<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一个登录页面</title>
    <link rel="stylesheet" type="text/css" href="css/Login_style.css"/>
</head>
<body>

<div class="login-container">
    <h2>登录</h2>
    <form action="LoginTest" method="post">
        <input type="text" placeholder="用户名" name="user_name" required>
        <input type="password" placeholder="密码" name="password" required>
        <button id="Login-btn" type=submit>登录</button>
        <button id="Register-btn" onclick="Register()">注册</button>
    </form>
</div>

<button id="Hall-btn" onclick="BackToHall()">
    返回大厅!
</button>
<%--ersfdghj--%>
<%--<>--%>
<%--casdassadadaddzcacxaca--%>
<%--wssb--%>
<%--afsdfsdafdghad--%>
<%--tserdfgyhj--%>
<script>
    function Login(){
        alert('666');
    }

    function Register(){
        alert('即将跳转至注册页面!');
        window.location.href = "Register.jsp";
    }

    function BackToHall(){
        alert('即将回到大厅！');
        window.location.href = "Hall.jsp"
    }
</script>
</body>
</html>
