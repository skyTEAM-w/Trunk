<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%
    // 获取应用程序的上下文路径和基本路径
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
    <title>车辆状态监控</title>

    <!-- 引入样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/VehicleStatus_style.css"/>
</head>

<div class="Back-Container">
    <div class="Head-Container">
            <!-- 表单用于输入车辆编号 -->
            <form action="VehicleStatusController" method="post">
                <label for="VehicleID">请输入车辆编号：</label>
                <input type="text" id="VehicleID" name="VehicleName" placeholder="请输入车辆编号">
                <button id="GetStatus-btn" type="submit">获取状态</button>
            </form>
    </div>
    <div class="Body-Container" style="overflow-y: scroll; margin-top: 30px;">
            <table class="Status-Container">
                <thead>
                <tr>
                    <th>维护状态</th>
                    <th>剩余维护时间</th>
                    <th>故障状态</th>
                    <th>上次维护时间</th>
                    <th>维护次数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${Data1}</td>
                    <td>${Data2}</td>
                    <td>${Data3}</td>
                    <td>${Data4}</td>
                    <td>${Data5}</td>
                </tr>
                </tbody>
            </table>
    </div>
</div>

</html>
