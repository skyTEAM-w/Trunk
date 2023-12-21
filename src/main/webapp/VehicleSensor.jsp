<%--
  Created by IntelliJ IDEA.
  User: zzj & LTA & Copilot
  Date: 2023/12/6
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>车辆状态监控</title>

    <!-- 引入 Bootstrap 样式表文件 -->
    <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css">
    <!-- 引入自定义样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/VehicleSensor_style.css"/>
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
            <!-- 表单用于输入车辆编号和运行轮数 -->
            <form action="SensorController" method="post" class="d-flex justify-content-center align-items-center">
                <div class="input-group mb-3">
                    <input type="text" id="VehicleID" name="VehicleName" placeholder="车辆编号" class="form-control" aria-label="VehicleID" aria-describedby="basic-addon1">

                    <button id="GetStatus-btn" type="submit" class="btn btn-primary">查询</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <!-- 使用表格组件展示车辆传感器数据 -->
            <!-- 将表格放在一个自定义的容器中，并添加 table-grid 类名 -->
            <div class="table-container">
                <table class="table table-striped table-hover table-responsive table-grid">
                    <thead>
                    <!-- 将表头和数据分为两个区域，并添加 table-header 和 table-data 类名 -->
                    <tr>
                        <th>运行轮数</th>
                        <th>setting1</th>
                        <th>setting2</th>
                        <th>setting3</th>
                        <th>风扇进口总温度</th>
                        <th>低压压气机出口总温度</th>
                        <th>高压压气机出口总温度</th>
                        <th>低压涡轮出口总温度</th>
                        <th>风扇进口压强</th>
                        <th>旁路管道总压强</th>
                        <th>高压压气机出口总压强</th>
                        <th>风扇物理转速</th>
                        <th>核心机物理转速</th>
                        <th>发动机压力比</th>
                        <th>高压压气机出口静压（PS30）</th>
                        <th>燃油流量与高压压气机出口静压的比率</th>
                        <th>风扇换算转速</th>
                        <th>核心机换算转速</th>
                        <th>涵道比</th>
                        <th>燃烧器油气比</th>
                        <th>抽汽焓</th>
                        <th>需求风扇转速</th>
                        <th>需求风扇换算转速</th>
                        <th>高压涡轮冷气流量</th>
                        <th>低压涡轮冷气流量</th>
                    </tr>
                    <c:forEach var="data" items="${dataList}" varStatus="status">
                        <c:if test="${(status.index+1) % 25 == 1}">
                            <tr>
                        </c:if>
                        <c:forEach var="value" items="${data}">
                            <td>
                                    ${value}
                            </td>
                        </c:forEach>
                        <c:if test="${(status.index+1) % 25 == 0}">
                            </tr>
                        </c:if>
                    </c:forEach>
                    </thead>



                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>