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
    <form>
        <label for="vehicleNumber">输入车辆编号：</label>
        <input type="text" id="vehicleNumber" placeholder="请输入车辆编号">
        <button id="GetStatus-btn" onclick="getVehicleStatus()">获取状态</button>
    </form>
</div>
<div class="Status-Container">
    <!-- 状态信息将在此显示 -->333<br>666
</div>

<script>
    function getVehicleStatus() {
        // 获取输入的车辆编号
        var vehicleNumber = document.getElementById('vehicleNumber').value;

        // 发送请求到后端获取车辆状态
        fetch('/getVehicleStatus?vehicleNumber=' + vehicleNumber)
            .then(response => response.json())
            .then(data => {
                displayVehicleStatus(data);
            })
            .catch(error => {
                console.error('获取车辆状态失败：', error);
            });
    }

    function displayVehicleStatus(status) {
        // 清空状态容器
        document.getElementById('statusContainer').innerHTML = '';

        // 创建状态信息的HTML元素并添加到容器中
        var statusDiv = document.createElement('div');
        statusDiv.innerHTML = `<p>车辆编号：${status.vehicleNumber}</p>
                               <p>维护状态：${status.maintenanceStatus}</p>
                               <p>预计维护剩余时间：${status.remainingMaintenanceTime} 小时</p>
                               <p>曾发生故障状态：${status.hasFault ? '是' : '否'}</p>`;

        document.getElementById('statusContainer').appendChild(statusDiv);
    }
</script>

</body>
</html>
