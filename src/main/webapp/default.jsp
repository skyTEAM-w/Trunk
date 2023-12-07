<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/12/6
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3D场景</title>
    <!-- 导入阿里的图标样式文件 -->
    <script src="<%=path%>/webjars/three/0.155.0/build/three.js"></script>

</head>

<body>
    <div id="scene-container"></div>
</body>
<script>
    // Your Three.js code
    var container = document.getElementById('scene-container');
    var scene = new THREE.Scene();
    var camera = new THREE.PerspectiveCamera(75, container.clientWidth / container.clientHeight, 0.1, 1000);
    camera.position.z = 5;
    var renderer = new THREE.WebGLRenderer();
    renderer.setSize(container.clientWidth, container.clientHeight);
    container.appendChild(renderer.domElement);

    var light = new THREE.AmbientLight(0xffffff);
    scene.add(light);

    // Add your Three.js code here
    // ...

    function animate() {
        requestAnimationFrame(animate);
        renderer.render(scene, camera);
    }

    animate();
</script>

<style>
    #scene-container {
        width: 100%;
        height: 500px; /* 调整为适当的高度 */
    }
</style>
</html>
