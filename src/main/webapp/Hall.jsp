<%--<%@ page import="jakarta.servlet.http.HttpSession" %>--%>
<%--<%@ page import="java.util.Objects" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>

<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title></title>--%>

<%--    <!-- 引入样式表文件 -->--%>
<%--    <link rel="stylesheet" type="text/css" href="css/Hall_style.css" />--%>
<%--</head>--%>

<%--<body>--%>

<%--<!-- 顶部横向的 header -->--%>
<%--<header>--%>
<%--    <p id="username">--%>
<%--    <div id="logoutButtonContainer">--%>
<%--        <button id="logoutButton" onclick="logout()">退出登录</button>--%>
<%--    </div>--%>
<%--</header>--%>

<%--<div class="wrapper">--%>
<%--    <!-- 侧边栏导航 -->--%>
<%--    <nav id="sidebar">--%>
<%--        <ul class="list-unstyled components">--%>
<%--            <li>--%>
<%--                <a href="#faultDetectionSubMenu" data-toggle="collapse" aria-expanded="false"--%>
<%--                   class="dropdown-toggle">故障检测和预测性维护</a>--%>
<%--                <ul class="collapse list-unstyled" id="faultDetectionSubMenu">--%>
<%--                    <li><a href="Detection.jsp">功能1</a></li>--%>
<%--                    <li><a href="#">选项1</a></li>--%>
<%--                    <li><a href="#">选项2</a></li>--%>
<%--                    <!-- 可以添加更多子菜单项 -->--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="VehicleStatus.jsp">车辆信息监控</a>--%>
<%--            </li>--%>
<%--            <!-- 可以添加更多导航项 -->--%>
<%--        </ul>--%>
<%--    </nav>--%>

<%--    <!-- 功能内容区域 -->--%>
<%--    <div id="content">--%>
<%--        <!-- 这里将显示所选功能的内容 -->--%>
<%--        <!-- 默认显示欢迎信息或其他内容 -->--%>
<%--        <h2>Welcome to Intelligent Vehicle Hall!</h2>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script>--%>
<%--    var sidebar = document.getElementById('sidebar');--%>
<%--    var content = document.getElementById('content');--%>

<%--    sidebar.addEventListener('click', function (event) {--%>
<%--        event.preventDefault();--%>
<%--        var pageHref = event.target.getAttribute('href');--%>

<%--        if (event.target.parentElement.classList.contains('dropdown-toggle')) {--%>
<%--            handleDropdownItemClick(event);--%>
<%--        } else {--%>
<%--            loadPageContent(pageHref);--%>
<%--        }--%>
<%--    });--%>

<%--    function loadPageContent(pageHref) {--%>
<%--        fetch(pageHref)--%>
<%--            .then(function (response) {--%>
<%--                return response.text();--%>
<%--            })--%>
<%--            .then(function (contentHTML) {--%>
<%--                content.innerHTML = contentHTML;--%>
<%--                initializeStyles();--%>
<%--            })--%>
<%--            .catch(function (error) {--%>
<%--                console.error('Error loading page content:', error);--%>
<%--            });--%>
<%--    }--%>

<%--    function initializeStyles() {--%>
<%--        initializeSidebarStyles();--%>
<%--        // 可以添加其他样式初始化逻辑--%>
<%--    }--%>

<%--    function initializeSidebarStyles() {--%>
<%--        // 添加点击事件监听器--%>
<%--        sidebar.addEventListener('click', function (event) {--%>
<%--            event.preventDefault();--%>
<%--            var pageHref = event.target.getAttribute('href');--%>
<%--            if (event.target.parentElement.classList.contains('dropdown-toggle')) {--%>
<%--                handleDropdownItemClick(event);--%>
<%--            } else {--%>
<%--                loadPageContent(pageHref);--%>
<%--            }--%>
<%--        });--%>

<%--        // 可以添加其他侧边栏样式初始化逻辑--%>
<%--    }--%>

<%--    function handleDropdownItemClick(event) {--%>
<%--        var dropdownItemHref = event.target.getAttribute('href');--%>
<%--        loadPageContent(dropdownItemHref);--%>
<%--    }--%>

<%--    function logout() {--%>
<%--        var xhr = new XMLHttpRequest();--%>
<%--        xhr.open("GET", "Logout", true);--%>
<%--        xhr.onload = function () {--%>
<%--            if (xhr.status === 200) {--%>
<%--                console.log("Logout successful");--%>
<%--                window.location.href = "Login.jsp";--%>
<%--            } else {--%>
<%--                console.error("Logout failed");--%>
<%--            }--%>
<%--        };--%>
<%--        xhr.send();--%>
<%--    }--%>

<%--    document.addEventListener("DOMContentLoaded", function () {--%>
<%--        initializeStyles();--%>
<%--    });--%>
<%--</script>--%>

<%--<%!--%>
<%--    String getUserName(HttpSession session) {--%>
<%--        String systemAdmin = (String) session.getAttribute("systemAdmin");--%>
<%--        return Objects.requireNonNullElse(systemAdmin, "游客");--%>
<%--    }--%>
<%--%>--%>

<%--</body>--%>

<%--</html>--%>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>维护系统</title>
    <!-- 导入阿里的图标样式文件 -->
    <link rel="stylesheet" href="./css/iconfont.css">
    <link rel="stylesheet" href="./css/Hall_style.css">
</head>

<body>
<div class="content">
    <!-- Header 开始 -->
    <header>
        <p id="username"><%= getUserName(httpSession)%>
        </p>
        <div id="logoutButtonContainer">
            <button id="logoutButton" onclick="logout()">退出登录</button>
        </div>
    </header>
    <!-- Header 结束 -->
    <div class="mainbody">
    <div class="menu-box">
        <!-- 展示与隐藏侧边栏 开始-->
        <input type="checkbox" id="menu-btn">
        <label for="menu-btn"><i class="iconfont icon-yemianfanhui"></i></label>
        <!-- 展示与隐藏侧边栏 结束-->
        <div class="menu">
            <!-- 侧边栏的标题 开始-->
            <div class="menu-title">
                <h1>Menu</h1>
            </div>
            <!-- 侧边栏的标题 结束-->

            <!-- 每一项导航 -->
            <div class="menu-item">
                <input type="checkbox" id="menu-item1">
                <label for="menu-item1">
                    <i class="menu-item-icon iconfont icon-a-01-shujuzhongxin"></i>
                    <span>选项</span>
                    <i class="menu-item-last iconfont icon-down"></i>
                </label>
                <div class="menu-content">
                    <span>状态更新</span>
                    <span>故障检测</span>
                    <span>预测性维护</span>
                    <span>车辆信息监控</span>
                </div>
            </div>
            <!-- 其他导航项可以继续添加 -->

        </div>
    </div>
        <div class="component">123</div>
    </div>
</div>
</body>

<%!
    String getUserName(HttpSession session) {
        String systemAdmin = (String) session.getAttribute("systemAdmin");
        return Objects.requireNonNullElse(systemAdmin, "游客");
    }
%>

<script>
    function logout() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "Logout", true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log("Logout successful");
                window.location.href = "Login.jsp";
            } else {
                console.error("Logout failed");
            }
        };
        xhr.send();
    }
</script>

</html>