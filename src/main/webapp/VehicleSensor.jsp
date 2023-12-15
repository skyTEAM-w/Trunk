<%--
  Created by IntelliJ IDEA.
  User: zzj
  Date: 2023/12/6
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>车辆状态监控</title>

    <!-- 引入样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/VehicleSensor_style.css"/>
</head>

<div>
    <!-- 表单用于输入车辆编号和运行轮数 -->
    <form action="SensorController" method="post">
        <label for="VehicleID">输入车辆编号：</label>
        <input type="text" id="VehicleID" name="VehicleName" placeholder="车辆id">
        <label for="cycle">输入车辆运行轮数：</label>
        <input type="text" id="cycle" name="cycle" placeholder="车辆运行轮数">
        <button id="GetStatus-btn" type="submit">查询</button>
    </form>
    <table class="Sensor-Container">
        <thead>
        <tr>
            <th>列数</th>
            <th>车辆ID</th>
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
        </thead>
        <tbody>
        <tr>
            <td>${Data0}</td>
            <td>${Data1}</td>
            <td>${Data2}</td>
            <td>${Data3}</td>
            <td>${Data4}</td>
            <td>${Data5}</td>
            <td>${Data6}</td>
            <td>${Data7}</td>
            <td>${Data8}</td>
            <td>${Data9}</td>
            <td>${Data10}</td>
            <td>${Data11}</td>
            <td>${Data12}</td>
            <td>${Data13}</td>
            <td>${Data14}</td>
            <td>${Data15}</td>
            <td>${Data16}</td>
            <td>${Data17}</td>
            <td>${Data18}</td>
            <td>${Data19}</td>
            <td>${Data20}</td>
            <td>${Data21}</td>
            <td>${Data22}</td>
            <td>${Data23}</td>
            <td>${Data24}</td>
            <td>${Data25}</td>
            <td>${Data26}</td>
        </tr>
        </tbody>
    </table>
</div>

</html>