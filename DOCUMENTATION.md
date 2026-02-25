# 重型卡车预测性维护系统 (Truck Predictive Maintenance System) V1.0

## 项目概述
本项目是一个基于Java Web技术的重型卡车预测性维护系统，旨在通过收集和分析卡车传感器数据，预测车辆维护需求和故障风险。系统集成了Python深度学习模型（PyTorch/DJL）进行故障预测。

## 架构演进

### 当前架构 (Legacy)
- **后端**: Java Servlets, JSP, JDBC (手动管理连接), XML配置 (`web.xml`, `db_connect.xml`, `python_host.xml`).
- **前端**: JSP, HTML, CSS, JavaScript (Bootstrap, jQuery).
- **部署**: WAR包部署在Tomcat容器中.
- **数据库**: MySQL 8.0.

### 现代架构 (Refactored)
- **后端框架**: Spring Boot 3.x (Java 17).
- **Web层**: Spring MVC (`@Controller`, `@RestController`).
- **数据访问**: Spring JDBC (`JdbcTemplate`) / Spring Data JDBC.
- **配置**: `application.yml` (集中化配置, 类型安全).
- **前端模板**: Thymeleaf (替代JSP, 支持独立JAR部署).
- **部署**: 可执行JAR (内嵌Tomcat).
- **外部集成**: 
  - Python服务集成: 通过HTTP REST API通信.
  - 路径管理: 所有配置路径相对化，严禁硬编码C盘路径.

## 环境要求
- **JDK**: 17+
- **Maven**: 3.8+
- **MySQL**: 8.0+ (请确保安装路径非C盘，或使用便携版)
- **Python环境**: 用于运行预测模型服务 (需单独配置，建议放置在项目同级目录).

## 本地部署指南

### 1. 启动脚本 (run.bat)
项目根目录下提供了 `run.bat` 脚本，用于一键构建并启动应用。该脚本会自动设置环境变量，确保生成的缓存文件（如DJL模型缓存）存储在项目目录下的 `djl_cache` 文件夹中，避免占用C盘空间。

### 2. 数据库配置
修改 `src/main/resources/application.yml` 中的数据库连接信息:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/game?useSSL=false&serverTimezone=UTC
    username: root
    password: your_password
```
若需初始化数据库，请使用 `src/main/resources/DataBase.sql` 脚本。

### 3. Python服务配置
修改 `src/main/resources/application.yml` 中的Python服务地址:
```yaml
app:
  python:
    url: http://localhost
    port: 11200
    router: /app
```
**注意**: Python服务代码不在本项目仓库中，请确保Python服务已独立启动并监听上述端口。

### 4. 运行
双击 `run.bat` 或在命令行执行:
```bash
./run.bat
```
访问: `http://localhost:8080`

## API 参考
| 功能 | 旧版 Servlet | 新版 Controller 路径 | 描述 |
| :--- | :--- | :--- | :--- |
| 登录 | `/Login` | `/login` | 用户登录 |
| 车辆状态 | `/VehicleStatusController` | `/vehicle/status` | 查询车辆维护状态 |
| 传感器数据 | `/SensorController` | `/sensor/data` | 查询传感器CSV数据 |
| 故障检测 | `/VSDetective` | `/detect` | 触发故障检测逻辑 |
