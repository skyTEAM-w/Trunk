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
        <button id="Register-btn" type="submit" style="width: 100%">注册</button>
    </form>
    <form action="ReBackToLo" method="get">
        <input type="submit" value="点击回到登录页面"
               <%--按钮属性，改了按钮就会错位！-From DDD--%>
               style="padding: 10px;
                    border: none;
                    border-radius: 5px;
                    background-color: #4CAF50;
                    color: #fff;
                    cursor: pointer;
                    margin: 10px auto auto auto;">
    </form>
</div>

</body>
</html>
