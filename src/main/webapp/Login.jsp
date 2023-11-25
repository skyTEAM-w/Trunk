<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一个登录页面</title>
    <link rel="stylesheet" type="text/css" href="css/Login_style.css"/>
</head>
<body>

<div class="login-container">
    <h2>登录</h2>
    <form>
        <input type="text" placeholder="用户名" required>
        <input type="password" placeholder="密码" required>
        <button id="Login-btn" type=submit onclick="Login()">登录</button>
        <button id="Register-btn" onclick="Register()">注册</button>
    </form>
</div>

<script>
    function Login(){
        alert('666');
    }

    function Register(){
        alert('即将跳转至注册页面!');
        window.location.href = "Register.jsp";
    }
</script>
</body>
</html>
