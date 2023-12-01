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
</head>
<body>

<h1>车辆状态监控</h1>

<div class="Text-Container">
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

    </script>
</div>

<%--动态更新Status-Container--%>
<%--<script type="text/javascript">
    function updateData() {
        var formData = $('#VehicleStatusForm').serialize();

        $.ajax({
            url: '/api/VehicleStatus/latestData',
            method: 'POST',
            data: formData,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (Data) {
                var jsonObj = eval(Data);
                var display = document.getElementById('Status-Container');
                display.innerHTML = '<p>车辆编号：' + jsonObj[0] + '</p>';
            },
            error: function (error) {
                alert('ERROR');
            }
        });
    }
</script>--%>
</body>
</html>
