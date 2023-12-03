<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页面</title>
    <script>
        function showLogin() {
            document.getElementById('contentContainer').innerHTML = '';
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById('contentContainer').innerHTML = this.responseText;
                }
            };
            xhr.open("GET", "Login.jsp", true);
            xhr.send();
        }

        function showRegister() {
            document.getElementById('contentContainer').innerHTML = '';
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById('contentContainer').innerHTML = this.responseText;
                }
            };
            xhr.open("GET", "Register.jsp", true);
            xhr.send();
        }
    </script>
</head>
<body>
<div>
    <button onclick="showLogin()">显示登录</button>
    <button onclick="showRegister()">显示注册</button>
</div>
<div id="contentContainer">
    <%--默认显示登录页面--%>
    <%@include file="Login.jsp" %>
</div>
<!-- 可以在这里添加其他内容 -->
</body>
</html>
