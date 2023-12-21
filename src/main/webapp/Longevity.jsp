<!-- @page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
String path=request.getContextPath(); String basePath=request.getScheme() + "://" + request.getServerName() + ":"
+ request.getServerPort() + path + "/" ;  -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预计使用寿命</title>
    <!-- 外部CSS链接 -->
    <link rel="stylesheet" href="css/Longevity_style.css">
</head>

<body>
<div class="longevity-container">
    <form action="Longevity" method="post">
        <label for="VehicleID">请输入车辆id：</label>
        <input type="text" id="VehicleID" name="VehicleName" placeholder="请输入车辆id">
        <button id="GetStatus-btn" type="submit">预测</button>
    </form>
    <div class="paragraph-container">
        <p>预计使用寿命：${Longevity}</p>
    </div>
</div>
</body>

</html>