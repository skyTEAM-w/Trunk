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
<body>

<h1>车辆状态监控</h1>

<div class="Text-Container">
    <!-- 表单用于输入车辆编号 -->
    <form action="VehicleStatusController" method="post">
        <label for="VehicleID">输入车辆编号：</label>
        <input type="text" id="VehicleID" name="VehicleName" placeholder="请输入车辆编号">
        <button id="GetStatus-btn" type="submit">获取状态</button>
    </form>
</div>

<div class="Status-Container">
    <!-- 状态信息将在此显示 -->
    ${jsonData}
    <script>
        // 可以在这里添加JavaScript代码进行一些动态操作
    </script>
</div>

<%--动态更新Status-Container（注释部分的Ajax代码）--%>
<%--<script type="text/javascript">
    function updateData() {
        // 使用jQuery的ajax方法向服务器发送请求
        var formData = $('#VehicleStatusForm').serialize();

        $.ajax({
            url: '/api/VehicleStatus/latestData',
            method: 'POST',
            data: formData,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (Data) {
                // 处理成功响应，更新显示状态信息
                var jsonObj = eval(Data);
                var display = document.getElementById('Status-Container');
                display.innerHTML = '<p>车辆编号：' + jsonObj[0] + '</p>';
            },
            error: function (error) {
                // 处理错误响应
                alert('ERROR');
            }
        });
    }
</script>--%>
</body>
</html>
