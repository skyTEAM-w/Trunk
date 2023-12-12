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
    <script src="<%=path%>/js/Hall.js"></script>
</head>

<body>
<div class="content">
    <!-- Header 开始 -->
    <header>
        <p id="username">欢迎您！<%= getUserName(httpSession)%>
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
                        <span onclick="updateComponent('Detection')">状态更新</span>
                        <span onclick="updateComponent('Fix')">故障检测</span>
                        <span onclick="updateComponent('Longevity')">预测性维护</span>
                        <span onclick="updateComponent('VehicleStatus')">车辆信息监控</span>
                        <span onclick="updateComponent('VehicleSensor')">车辆传感器信息查询</span>
                    </div>
                </div>
                <!-- 其他导航项可以继续添加 -->

            </div>
        </div>
        <div class="component" >
            <iframe id="componentContainer" style="height: 100%; width: 100%;border: 0;" src="default.jsp"></iframe>
        </div>
    </div>
</div>
</body>

<%!
    String getUserName(HttpSession session) {
        String systemAdmin = (String) session.getAttribute("systemAdmin");
        return Objects.requireNonNullElse(systemAdmin, "游客");
    }
%>


</html>