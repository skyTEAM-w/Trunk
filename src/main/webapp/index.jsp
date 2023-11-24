<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一个登录页面</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background: linear-gradient(to right, #DE6262, #FFB88C);
            color: #fff; /* 文字颜色 - 白色 */
            font-family: 'Arial', sans-serif;
        }

        .login-container {
            background-color: whitesmoke;/* 登录框背景色 - 深灰色 */
            padding: 100px 300px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(255, 255, 255, 0.1); /* 模拟光晕效果 */
            width: 300px;
            text-align: center;
        }

        h2 {
            color: gray; /* 登录标题颜色 - 绿色 */
            font-size: 60px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
            border: 1px solid #fff; /* 输入框边框颜色 - 白色 */
            border-radius: 5px;
            background-color: #535252; /* 输入框背景色 - 深灰色 */
            color: #fff; /* 输入框文字颜色 - 白色 */
        }

        #Login-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }

        #Register-btn {
            background-color: #790b0b;
            color:white;
            border: none;
            padding:10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            text-decoration: none;
            margin: 4px 2px 4px 44px;
            cursor: pointer;
            border-radius: 5px;
        }
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>登录</h2>
    <form>
        <input type="text" placeholder="用户名" required>
        <input type="password" placeholder="密码" required>
        <button id="Login-btn" onclick="Login">登录</button>
        <button id="Register-btn" type=submit onclick="Register">注册</button>
    </form>
</div>

<script>
    function Login{

    }

    function Register{

    }
</script>

</body>
</html>
