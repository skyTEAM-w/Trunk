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
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/Login_style.css"/>
</head>
<body>
<%--一个注释--%>
<div class="login-container" id="login-container">
    <h2>登录</h2>
    <form action="Login" method="post">
        <input type="text" placeholder="用户名" name="user_name" required>
        <input type="password" placeholder="密码" name="password" required>
        <button id="Login-btn" type=submit style="width: 100%">登录</button>
    </form>
    <form action="LoToRe" method="get">
        <input type="submit" value="注册"
        <%--按钮属性，改了按钮就会错位！-From DDD--%>
               style="background-color: #4285f4;
                        color:white;
                        width: 100%;
                        border: none;
                        padding:10px;
                        text-align: center;
                        text-decoration: none;
                        display: inline-block;
                        font-size: 16px;
                        margin: 10px auto auto auto;
                        cursor: pointer;
                        border-radius: 5px;">
    </form>
</div>
</body>
</html>