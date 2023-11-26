<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>智能车辆大厅</title>
    <link rel="stylesheet" type="text/css" href="css/Hall_style.css"/>
</head>
<body>

<div class="hall-container">
    <h1>智能车辆大厅</h1>

    <div class="function-buttons">
        <button onclick="goToLogin()">登录/注册</button>
        <button onclick="goToFaultDetection()">故障检测和预测性维护</button>
        <button onclick="goToVehicleMonitoring()">车辆信息监控</button>
    </div>
</div>

<script>
    function goToLogin() {
        window.location.href = "Login.jsp";
    }

    function goToFaultDetection() {
        window.location.href = "Detection.jsp";
    }

    function goToVehicleMonitoring() {
        window.location.href = "VehicleStatus.jsp";
    }
</script>

</body>
</html>
