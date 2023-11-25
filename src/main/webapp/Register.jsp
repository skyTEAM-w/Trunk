<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Register_style.css"/>
</head>
<body>

<div class="registration-container">
    <h2>注册</h2>
    <form id="registrationForm" onsubmit="return validateForm()">
        <input type="text" id="username" placeholder="用户名" required>
        <input type="email" id="email" placeholder="邮箱" required>
        <input type="password" id="password" placeholder="密码" required>
        <input type="password" id="confirmPassword" placeholder="确认密码" required>
        <button id="Register-btn" type="submit">注册</button>
        <button id="BackToLogin-btn" onclick="BackToLogin()">点此返回登录页面</button>
    </form>
</div>

<script>
    function validateForm() {
        // 获取表单中的值
        var username = document.getElementById('username').value;
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword').value;

        // 简单的密码确认
        if (password !== confirmPassword) {
            alert('密码不一致，请重新输入！');
            return false;
        }

        // 在实际应用中，可以添加更多的验证逻辑，例如检查密码强度、邮箱格式等

        // 如果所有验证通过，允许提交
        return true;
    }

    function BackToLogin(){
        alert('即将跳转！');
        window.location.href = "Login.jsp";
    }
</script>

</body>
</html>
