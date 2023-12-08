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
    <title>预计使用寿命</title>
    <!-- 外部CSS链接 -->
    <link rel="stylesheet" href="css/Longevity_style.css">
</head>

<body>
<div class="longevity-container">
    <div class="page-header">
        <p>id:123</p>
        <button id="File-btn" type="button">检测</button>
    </div>
    <div class="paragraph-container">
        <p>故障类型：</p>
    </div>
</div>
</body>

</html>