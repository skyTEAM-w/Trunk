<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预计使用寿命</title>
    <link rel="stylesheet" href="css/Longevity_style.css">
</head>

<body>
<div class="longevity-container" method="">
    <h3>预计使用寿命:</h3>
<%--    使用寿命将在此显示--%>
    <p th:text="${msg}" style="color: red"></p>
</div>
</body>

</html>