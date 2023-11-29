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
    <title>车辆状态监控</title>
    <link rel="stylesheet" type="text/css" href="css/VehicleStatus_style.css"/>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<h1>车辆状态监控</h1>
<p id="Status-Container" class="Status-Container">
    <%--车辆状态将显示在这里--%>
</p>
<button onclick="updateData()">点击</button>

<script type="text/javascript">
    function updateData() {
        $.ajax({
            url: '/api/VehicleStatus/latestData',
            method: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (Data) {
                let jsonObj = eval(Data);
                var display = document.getElementById('Status-Container');
                display.innerHTML = '<p>车辆编号：' + jsonObj[0] + '</p>';
                alert('Fuck You');
            },
            error: function (error) {
                var display = document.getElementById('Status-Container');
                display.innerHTML = "ERROR";
            }
        });
    }
    /*function getDataAndUpdate() {
        // 使用 Fetch API 发送请求到后端 Servlet
        fetch('VehicleStatusController')
            .then(response => response.text())
            .then(data => {
                // 获取数据成功后更新页面容器
                updateContainer(data);
            })
            .catch(error => {
                console.error('获取数据失败：', error);
            });
    }*/
    function updateContainer() {
        var display = document.getElementById('Status-Container');
        display.innerHTML = "<p>车辆编号:666</p>";
    }
</script>
</body>
</html>
