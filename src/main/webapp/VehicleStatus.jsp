<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%
    // 获取应用程序的上下文路径和基本路径
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>车辆状态监控</title>

    <!-- 引入 Bootstrap 样式表文件 -->
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css">
    <!-- 引入 Font Awesome 图标库文件 -->
    <link href="https://cdn.bootcss.com/font-awesome/5.8.2/css/fontawesome.min.css" rel="stylesheet">
    <!-- 引入自定义样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/VehicleStatus_style.css"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h1 class="text-center mt-4 mb-4">车辆状态监控</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <!-- 表单用于输入车辆编号 -->
            <form action="VehicleStatusController" method="post" class="d-flex justify-content-center align-items-center">
                <div class="input-group mb-3">

                    <input type="text" id="VehicleID" name="VehicleName" placeholder="请输入车辆编号" class="form-control" aria-label="VehicleID" aria-describedby="basic-addon1">
                    <button id="GetStatus-btn" type="submit" class="btn btn-primary"> 获取状态</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <!-- 使用卡片组件展示车辆状态 -->
            <div class="card-group">
                <div class="card text-white bg-success mb-3" style="max-width: 18rem;">
                    <div class="card-header">维护状态</div>
                    <div class="card-body">
                        <h5 class="card-title">${Data1}</h5>
                    </div>
                </div>
                <div class="card text-white bg-info mb-3" style="max-width: 18rem;">
                    <div class="card-header">剩余维护时间</div>
                    <div class="card-body">
                        <h5 class="card-title">${Data2}</h5>
                    </div>
                </div>
                <div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
                    <div class="card-header">故障状态</div>
                    <div class="card-body">
                        <h5 class="card-title">${Data3}</h5>
                    </div>
                </div>
                <div class="card text-white bg-warning mb-3" style="max-width: 18rem;">
                    <div class="card-header">上次维护时间</div>
                    <div class="card-body">
                        <h5 class="card-title">${Data4}</h5>
                    </div>
                </div>
                <div class="card text-white bg-secondary mb-3" style="max-width: 18rem;">
                    <div class="card-header">维护次数</div>
                    <div class="card-body">
                        <h5 class="card-title">${Data5}</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入 Bootstrap 脚本文件 -->
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
