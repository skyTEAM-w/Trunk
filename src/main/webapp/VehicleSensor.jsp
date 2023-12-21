<%--
  Created by IntelliJ IDEA.
  User: zzj & LTA & Copilot
  Date: 2023/12/6
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                    <input type="text" id="cycle" name="cycle" placeholder="运行轮数" class="form-control" aria-label="cycle" aria-describedby="basic-addon2">
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
                        <th class="table-header" style="text-align: left;">列数</th>
                        <td class="table-data">${Data0}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">车辆ID</th>
                        <td class="table-data">${Data1}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">维护状态</th>
                        <td class="table-data">${Data27}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">剩余维护时间</th>
                        <td class="table-data">${Data28}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">故障状态</th>
                        <td class="table-data">${Data29}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">上次维护时间</th>
                        <td class="table-data">${Data30}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">维护次数</th>
                        <td class="table-data">${Data31}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">运行轮数</th>
                        <td class="table-data">${Data2}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">setting1</th>
                        <td class="table-data">${Data3}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">setting2</th>
                        <td class="table-data">${Data4}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">setting3</th>
                        <td class="table-data">${Data5}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">风扇进口总温度</th>
                        <td class="table-data">${Data6}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">低压压气机出口总温度</th>
                        <td class="table-data">${Data7}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">高压压气机出口总温度</th>
                        <td class="table-data">${Data8}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">低压涡轮出口总温度</th>
                        <td class="table-data">${Data9}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">风扇进口压强</th>
                        <td class="table-data">${Data10}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">旁路管道总压强</th>
                        <td class="table-data">${Data11}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">高压压气机出口总压强</th>
                        <td class="table-data">${Data12}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">风扇物理转速</th>
                        <td class="table-data">${Data13}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">核心机物理转速</th>
                        <td class="table-data">${Data14}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">发动机压力比</th>
                        <td class="table-data">${Data15}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">高压压气机出口静压（PS30）</th>
                        <td class="table-data">${Data16}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">燃油流量与高压压气机出口静压的比率</th>
                        <td class="table-data">${Data17}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">风扇换算转速</th>
                        <td class="table-data">${Data18}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">核心机换算转速</th>
                        <td class="table-data">${Data19}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">涵道比</th>
                        <td class="table-data">${Data20}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">燃烧器油气比</th>
                        <td class="table-data">${Data21}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">抽汽焓</th>
                        <td class="table-data">${Data22}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">需求风扇转速</th>
                        <td class="table-data">${Data23}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">需求风扇换算转速</th>
                        <td class="table-data">${Data24}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">高压涡轮冷气流量</th>
                        <td class="table-data">${Data25}</td>
                    </tr>
                    <tr>
                        <th class="table-header" style="text-align: left;">低压涡轮冷气流量</th>
                        <td class="table-data">${Data26}</td>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>