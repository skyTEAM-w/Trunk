<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>故障检测和预测性维护</title>
    <link rel="stylesheet" href="css/Detection_style.css">
</head>
<body>
<!-- 外层容器 -->
<div class="detection-container">
    <h1>故障检测和预测性维护</h1>
    <!-- 车辆数据表单 -->
    <div class="VehicleData">
        <form action="DetectionTest" method="post" enctype="multipart/form-data">
            <!-- 文件上传部分 -->
            <div>
                <label for="upload">请选择要上传的数据：(.txt)</label>
                <input type="file" class="upload" name="upload" id="upload" accept=".txt" multiple>
            </div>
            <!-- 文件预览部分 -->
            <div class="preview">
                <p>目前还未选择文件</p>
            </div>
            <!-- 提交按钮 -->
            <div>
                <button id="File-btn" type="submit">提交</button>
            </div>
        </form>
    </div>
    <!-- 跳转按钮 -->
    <div class="jump">
        <button class="Detection" onclick="Detection()">故障检测</button>
        <button class="Fix" onclick="Fix()">预测性维护</button>
    </div>
</div>

<!-- JavaScript部分 -->
<script>
    // 跳转到故障检测页面
    function Detection() {
        window.location.href = "Longevity.jsp";
    }

    // 跳转到预测性维护页面
    function Fix() {
        window.location.href = "Fix.jsp";
    }

    // 获取页面中的文件上传元素和预览元素
    const input = document.querySelector('input');
    const preview = document.querySelector('.preview');

    // 将文件上传元素设为不可见
    input.style.opacity = 0;


    // 获取表单元素
    const form = document.querySelector('form');

    // 添加表单提交事件监听器
    form.addEventListener('submit', function (event) {
        // 获取当前选择的文件列表
        const curFiles = input.files;

        // 如果没有选择文件，阻止表单提交
        if (curFiles.length === 0) {
            alert('请先选择要上传的文件！');
            event.preventDefault();
        }
    });

    // 添加文件选择变化事件监听器
    input.addEventListener('change', updateImageDisplay);

    // 更新文件预览的函数
    function updateImageDisplay() {
        // 清空预览元素的子元素
        while (preview.firstChild) {
            preview.removeChild(preview.firstChild);
        }

        // 获取当前选择的文件列表
        const curFiles = input.files;
        if (curFiles.length === 0) {
            // 如果没有选择文件，显示提示信息
            const para = document.createElement('p');
            para.textContent = '目前还没选择文件';
            preview.appendChild(para);
        } else {
            // 如果选择了文件，创建一个有序列表
            const list = document.createElement('ol');
            preview.appendChild(list);

            // 遍历选择的文件列表
            for (const file of curFiles) {
                // 创建列表项和段落元素
                const listItem = document.createElement('li');
                const para = document.createElement('p');

                if (validFileType(file)) {
                    // 如果文件类型有效，显示文件名和大小
                    para.textContent = '文件名：' + file.name + ', 文件大小：' + returnFileSize(file.size) + '.';
                    //const image = document.createElement('img');
                    //image.src = URL.createObjectURL(file);

                    // 将图像元素添加到列表项
                    listItem.appendChild(para);
                } else {
                    // 如果文件类型无效，显示提示信息
                    para.textContent = '文件' + file.name + '为非有效文件类型';
                    listItem.appendChild(para);
                }

                // 将列表项添加到有序列表
                list.appendChild(listItem);
            }
        }
    }

    // 允许的文件类型
    const fileTypes = ['text/plain'];

    // 检查文件类型是否合法的函数
    function validFileType(file) {
        return fileTypes.includes(file.type);
    }

    // 返回文件大小的格式化字符串的函数
    function returnFileSize(number) {
        if (number < 1024) {
            return number + 'bytes';
        } else if (number > 1024 && number < 1048576) {
            return (number / 1024).toFixed(1) + 'KB';
        } else if (number > 1048576) {
            return (number / 1048576).toFixed(1) + 'MB';
        }
    }


</script>
</body>
</html>
