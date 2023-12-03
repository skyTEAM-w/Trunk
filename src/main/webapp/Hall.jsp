<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>智能车辆大厅</title>

    <!-- 引入样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/Hall_style.css"/>
</head>
<body>

<div class="hall-container">
    <!-- 页面标题 -->
    <h1>智能车辆大厅</h1>

    <!-- 功能按钮区域 -->
    <div class="function-buttons">
        <!-- 不同功能的按钮 -->
        <button id="faultDetectionButton">故障检测和预测性维护</button>
        <button id="vehicleMonitoringButton">车辆信息监控</button>
        <button id="logoutButton">退出登录</button>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 获取功能按钮区域的 DOM 元素
        var functionButtons = document.querySelector(".function-buttons");

        // 添加点击事件监听器
        functionButtons.addEventListener("click", function(event) {
            // 获取被点击的按钮
            var targetButton = event.target;

            // 检查点击的元素是否是按钮
            if (targetButton.tagName === "BUTTON") {
                // 获取按钮的id
                var buttonId = targetButton.id;

                // 根据按钮id执行相应的操作
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

        // 跳转到故障检测页面
        function goToFaultDetection() {
            window.location.href = "Detection.jsp";
        }

        // 跳转到车辆信息监控页面
        function goToVehicleMonitoring() {
            window.location.href = "VehicleStatus.jsp";
        }

        // 执行退出登录操作
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
