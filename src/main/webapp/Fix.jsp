


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预测故障</title>
    <link rel="stylesheet" href="css/Fix_style.css">
</head>

<body>
<div class="fix-container" method="">
    <h3>预测故障为:</h3>
    <%--    预测的故障将在此显示--%>
    <p th:text="${msg}" style="color: red"></p>
</div>
</body>

</html>