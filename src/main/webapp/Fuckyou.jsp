<!DOCTYPE html>
<html lang="en">

<head>
    <title>主页面</title>
    <script>
        function loadContent(page) {
            document.getElementById('contentContainer').innerHTML = '';

            fetch(page)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.text();
                })
                .then(data => {
                    document.getElementById('contentContainer').innerHTML = data;

                    // 在加载新内容后重新执行 JavaScript 代码
                    const scriptTags = document.querySelectorAll('#contentContainer script');
                    scriptTags.forEach(script => {
                        eval(script.innerHTML);
                    });
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</head>

<body>
<div>
    <button onclick="loadContent('Login.jsp')">显示登录</button>
    <button onclick="loadContent('Register.jsp')">显示注册</button>
    <button onclick="loadContent('Detection.jsp')">显示上传文件</button>
    <button onclick="loadContent('Longevity.jsp')">显示故障检测</button>
    <button onclick="loadContent('Fix.jsp')">显示预测性维护</button>
</div>
<div id="contentContainer">
    <%--默认显示登录页面--%>
    <%@include file="Login.jsp" %>
</div>
<!-- 可以在这里添加其他内容 -->
</body>

</html>
