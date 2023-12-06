console.log('Detection.jsp JavaScript code executed');

// 获取页面中的文件上传元素和预览元素
const input = document.querySelector('#upload');
const preview = document.querySelector('.preview');

// 获取提交按钮元素
const submitBtn = document.querySelector('#File-btn');

// 将文件上传元素设为不可见
input.style.opacity = 0;

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

// 添加提交按钮点击事件监听器
submitBtn.addEventListener('click', function () {
    // 创建FormData对象，用于包装表单数据
    const formData = new FormData(document.getElementById('uploadForm'));

    // 使用Ajax发送文件
    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'DetectionTest', true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            // 处理后台响应
            if (xhr.status === 200) {
                // 获取后台返回的数据
                const responseData = xhr.responseText;

                // 在页面上显示上传成功的消息
                document.getElementById('successMessage').style.display = 'block';
            } else {
                // 处理其他错误情况
                console.error('上传失败');
            }
        }
    };

    // 发送FormData对象
    xhr.send(formData);
});