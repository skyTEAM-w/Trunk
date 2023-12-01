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
        <button id="faultDetectionButton">故障检测和预测性维护</button>
        <button id="vehicleMonitoringButton">车辆信息监控</button>
        <button id="logoutButton">退出登录</button>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var functionButtons = document.querySelector(".function-buttons");

        functionButtons.addEventListener("click", function(event) {
            var targetButton = event.target;

            if (targetButton.tagName === "BUTTON") {
                var buttonId = targetButton.id;

                switch (buttonId) {
                    case "faultDetectionButton":
                        goToFaultDetection();
                        break;
                    case "vehicleMonitoringButton":
                        goToVehicleMonitoring();
                        break;
                    case "logoutButton":
                        logout();
                        break;
                    default:
                        break;
                }
            }
        });

        function goToFaultDetection() {
            window.location.href = "Detection.jsp";
        }

        function goToVehicleMonitoring() {
            window.location.href = "VehicleStatus.jsp";
        }

        function logout() {
            // 使用XMLHttpRequest对象发送GET请求
            var xhr = new XMLHttpRequest();

            // 设置请求方法和URL
            xhr.open("GET", "Logout", true);

            // 定义请求完成时的回调函数
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // 请求成功时的处理
                    console.log("Logout successful");
                    // 这里你可以根据后端返回的响应执行相应的前端逻辑
                    window.location.href = "Login.jsp";
                } else {
                    // 请求失败时的处理
                    console.error("Logout failed");
                }
            };

            // 发送请求
            xhr.send();
        }
    });

</script>

</body>
</html>
