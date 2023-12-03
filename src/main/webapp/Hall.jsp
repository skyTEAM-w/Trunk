<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@page import="com.whut.truck.entity.SystemAdmin" %>
<%@page import="java.lang.String" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Objects" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    HttpSession httpSession = (HttpSession) request.getSession();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>

    <!-- 引入样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/Hall_style.css"/>
</head>
<body>

<!-- 顶部横向的 header -->
<header>
    <p id="username"><%= getUserName(httpSession) %></p>
    <div id="logoutButtonContainer">
        <button id="logoutButton" onclick="logout()">退出登录</button>
    </div>
</header>

<div class="wrapper">
    <!-- 侧边栏导航 -->
    <nav id="sidebar">
        <ul class="list-unstyled components">
            <li>
                <a href="#faultDetectionSubMenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">故障检测和预测性维护</a>
                <ul class="collapse list-unstyled" id="faultDetectionSubMenu">
                    <li><a href="Detection.jsp">功能1</a></li>
                    <!-- 可以添加更多子菜单项 -->
                </ul>
            </li>
            <li>
                <a href="VehicleStatus.jsp">车辆信息监控</a>
            </li>
            <!-- 可以添加更多导航项 -->
        </ul>
    </nav>

    <!-- 功能内容区域 -->
    <div id="content">
        <!-- 这里将显示所选功能的内容 -->
        <!-- 默认显示欢迎信息或其他内容 -->
        <h2>Welcome to Intelligent Vehicle Hall!</h2>
    </div>
</div>

<script>
    var sidebar = document.getElementById('sidebar');

    // 获取内容区域的DOM元素
    var content = document.getElementById('content');

    // 添加点击事件监听器
    sidebar.addEventListener('click', function (event) {
        // 阻止默认的链接跳转行为
        event.preventDefault();

        // 获取被点击的链接的href属性值
        var pageHref = event.target.getAttribute('href');

        // 使用Ajax或其他方式加载页面内容
        loadPageContent(pageHref);
    });

    // 加载页面内容的函数
    function loadPageContent(pageHref) {
        // 使用Ajax或其他方式加载页面内容
        // 可以使用fetch或XMLHttpRequest

        // 示例：使用fetch
        fetch(pageHref)
            .then(function (response) {
                return response.text();
            })
            .then(function (contentHTML) {
                // 将加载的内容设置到功能内容区域
                content.innerHTML = contentHTML;
            })
            .catch(function (error) {
                console.error('Error loading page content:', error);
            });
    }

    // 执行退出登录操作
    function logout() {
        // 使用XMLHttpRequest对象发送GET请求
        var xhr = new XMLHttpRequest();

        // 设置请求方法和URL
        xhr.open("GET", "Logout", true);

        // 定义请求完成时的回调函数
        xhr.onload = function () {
            if (xhr.status === 200) {
                // 请求成功时的处理
                console.log("Logout successful");
                // 这里你可以根据后端返回的响应执行相应的前端逻辑
                window.location.href = "Login.jsp";
            } else {
                // 请求失败时的处理
                console.error("Logout failed");
            }
        };

        // 发送请求
        xhr.send();
    }

    // 在页面加载完成后调用的初始化函数
    document.addEventListener("DOMContentLoaded", function () {
        // 这里可以添加其他初始化逻辑
    });
</script>

<%!
    String getUserName(HttpSession session) {
        String systemAdmin = (String) session.getAttribute("systemAdmin");
        return Objects.requireNonNullElse(systemAdmin, "游客");
    }
%>

</body>
</html>
