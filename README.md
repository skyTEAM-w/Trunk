# 重型卡车预测性维护系统 (Truck Predictive Maintenance System)

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.2-green.svg)
![License](https://img.shields.io/badge/License-Proprietary-red.svg)

## 📖 项目概述

本项目是一个基于 **Spring Boot 3** 的现代化重型卡车预测性维护系统。系统通过收集和分析卡车传感器数据（如温度、压力、转速等），结合集成的深度学习模型（PyTorch/DJL），实时监测车辆状态，预测维护需求和故障风险，从而降低维护成本并提高车辆出勤率。

本项目已完成从传统 Servlet/JSP 架构向 Spring Boot + Thymeleaf 的全面重构，实现了配置集中化、部署自动化和架构现代化。

## ✨ 主要功能

- **用户鉴权**: 安全的登录与注册机制，支持会话管理。
- **车辆状态监控**: 实时展示车辆的维护状态、剩余维护时间、故障历史等关键指标。
- **传感器数据分析**: 可视化展示发动机各项传感器数据（如风扇进口温度、高压压气机压力等）。
- **故障检测与预测**:
  - 集成 DJL (Deep Java Library) 调用 PyTorch 模型。
  - 支持与外部 Python 预测服务通过 HTTP REST API 交互。
  - 提供故障类型诊断和剩余寿命预测。
- **3D 可视化**: 集成 Three.js 展示车辆或零部件的 3D 模型（部分实现）。
- **环境隔离**: 所有运行时文件和缓存严格限制在项目目录内，不占用系统盘（C盘）空间。

## 🛠 技术栈

- **后端**: Java 17, Spring Boot 3.1.2, Spring MVC, Spring JDBC (JdbcTemplate)
- **前端**: Thymeleaf, HTML5, CSS3, JavaScript (Bootstrap 5, jQuery, Three.js)
- **数据库**: MySQL 8.0+
- **AI/ML**: DJL (Deep Java Library), PyTorch (Native CPU)
- **构建工具**: Maven 3.8+
- **部署**: 内嵌 Tomcat (可执行 JAR)

## 📋 环境要求

在开始之前，请确保您的开发环境满足以下要求：

- **JDK**: OpenJDK 17 或更高版本
- **Maven**: 3.8 或更高版本
- **MySQL**: 8.0+ (建议安装在非系统盘)
- **Python 环境** (可选): 如果需要运行外部 Python 预测服务

## 🚀 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/skyTEAM-w/Trunk.git
cd trunk_project
```

### 2. 数据库初始化
1. 创建数据库 `game`。
2. 执行 `src/main/resources/DataBase.sql` 脚本初始化表结构和数据。
   ```sql
   source src/main/resources/DataBase.sql;
   ```

### 3. 配置文件
修改 `src/main/resources/application.yml` 以匹配您的环境：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/game?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8&allowPublicKeyRetrieval=true
    username: root      # 修改为您的数据库用户名
    password: password  # 修改为您的数据库密码

app:
  python:
    url: http://localhost # Python 预测服务地址
    port: 11200
    router: /app
```

### 4. 启动应用
本项目提供了便捷的启动脚本，会自动处理依赖构建和环境变量设置（如 DJL 缓存路径）。

**Windows (推荐):**
双击项目根目录下的 `run.bat`，或在终端执行：
```powershell
./run.bat
```

**命令行 (Maven):**
```bash
mvn clean package -DskipTests
java -jar target/truck_project.jar
```

启动成功后，访问: [http://localhost:8080](http://localhost:8080)

## 📚 API 与 页面路由

| 功能模块 | 新版路径 | 描述 |
| :--- | :--- | :--- |
| **认证** | `/login`, `/register` | 用户登录与注册页面 |
| **大厅** | `/hall` | 系统主界面/控制台 |
| **车辆状态** | `/VehicleStatusController` | 查询并展示车辆详细状态 |
| **传感器** | `/SensorController` | 加载并展示传感器 CSV 数据 |
| **故障检测** | `/Fix` | 故障检测页面与结果 |
| **寿命预测** | `/Longevity` | 剩余寿命预测页面 |
| **检测上传** | `/Detection` | 上传数据文件进行检测 |

## 📂 项目结构

```
src
├── main
│   ├── java
│   │   └── com.whut.truck
│   │       ├── config       # Spring 配置类 (WebConfig, PythonConfig)
│   │       ├── controller   # Web 控制器
│   │       ├── Dao          # 数据访问接口
│   │       ├── entity       # 实体类
│   │       ├── Service      # 业务逻辑层
│   │       └── utils        # 工具类 (CsvUtil, HttpCommunication)
│   └── resources
│       ├── static           # 静态资源 (CSS, JS, Fonts)
│       ├── templates        # Thymeleaf 模板 (HTML)
│       ├── application.yml  # 应用配置文件
│       └── DataBase.sql     # 数据库初始化脚本
└── test                     # 单元与集成测试
```

## 📝 变更日志 (v1.0.0)

- **架构重构**: 从 Servlet/JSP 迁移至 Spring Boot 3 + Thymeleaf。
- **代码优化**: 移除所有 XML 配置，采用 Java Config 和 YAML；使用 JdbcTemplate 替代手动 JDBC 管理。
- **环境隔离**: 增加 `run.bat` 脚本，将 DJL 缓存重定向至项目目录，彻底移除 C 盘硬编码路径。
- **功能增强**: 修复 CSV 数据导入逻辑，增加 3D 模型展示支持 (Three.js 依赖)。
- **测试覆盖**: 增加数据库集成测试，确保核心业务逻辑稳定性。

## 📄 许可证

本项目采用专有许可证。未经授权，禁止用于商业用途。

---
Copyright © 2023-2026 WHUT Truck Team. All Rights Reserved.
