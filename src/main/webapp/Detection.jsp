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
<div class="detection-container">
    <h1>故障检测和预测性维护</h1>
    <div class="VehicleData">
        <form action="@{/index}" method="post">
            <div>
                <label for="upload">请选择要上传的数据：(.txt)</label>
                <input type="file" class="upload" name="upload" id="upload" accept=".txt" multiple>
            </div>
            <div class="preview">
                <p>目前还未选择文件</p>
            </div>
            <div>
                <button>提交</button>
            </div>
        </form>
    </div>
    <div class="jump">
        <button class="Detection" onclick="Detection()">故障检测</button>
        <button class="Fix" onclick="Fix()">预测性维护</button>
    </div>
</div>
<script>
    function Detection() {
        window.location.href = "Longevity.jsp";
    }

    function Fix() {
        window.location.href = "Fix.jsp";
    }

    const input = document.querySelector('input');
    const preview = document.querySelector('.preview');

    input.style.opacity = 0;

    input.addEventListener('change', updateImageDisplay);

    function updateImageDisplay() {
        while (preview.firstChild) {
            preview.removeChild(preview.firstChild);
        }

        const curFiles = input.files;
        if (curFiles.length === 0) {
            const para = document.createElement('p');
            para.textContent = '目前还没选择文件';
            preview.appendChild(para);
        } else {
            const list = document.createElement('ol');
            preview.appendChild(list);

            for (const file of curFiles) {
                const listItem = document.createElement('li');
                const para = document.createElement('p');

                if (validFileType(file)) {
                    para.textContent = '文件名：' + file.name + ',文件大小：' + returnFileSize(file.size) + '.';
                    const image = document.createElement('img');
                    image.src = URL.createObjectURL(file);

                    listItem.appendChild(para);
                } else {
                    para.textContent = '文件' + file.name + '为非有效文件类型';
                    listItem.appendChild(para);
                }

                list.appendChild(listItem);
            }
        }
    }

    const fileTypes = ['text/plain'];

    function validFileType(file) {
        return fileTypes.includes(file.type);
    }

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
