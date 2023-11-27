<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="css/Register_style.css"/>
</head>
<body>

<div class="registration-container">
    <h2>注册</h2>
    <form action="Register" method="post">
        <input type="text" id="username" placeholder="用户名" name="username" required>
        <input type="email" id="email" placeholder="邮箱" name="email" required>
        <input type="password" id="password" placeholder="密码" name="password" required>
        <input type="password" id="confirmPassword" placeholder="确认密码" name="confirm_pass" required>
        <button id="Register-btn" type="submit">注册</button>
        <button id="BackToLogin-btn" onclick="BackToLogin()">点此返回登录页面</button>
    </form>
</div>

<script>
    function BackToLogin(){
        alert('即将跳转！');
        window.location.href = "Login.jsp";
    }
</script>

</body>
</html>
