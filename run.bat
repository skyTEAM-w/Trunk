@echo off
setlocal

echo [INFO] Setting up local environment...

:: Set DJL Cache Directory to project folder to avoid C drive usage
set "DJL_CACHE_DIR=%~dp0djl_cache"
if not exist "%DJL_CACHE_DIR%" mkdir "%DJL_CACHE_DIR%"
echo [INFO] DJL Cache Directory set to: %DJL_CACHE_DIR%

:: Check for Java
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Java is not found in PATH. Please install Java 17+ and add it to PATH.
    pause
    exit /b 1
)

:: Check for Maven
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Maven is not found in PATH. Please install Maven and add it to PATH.
    pause
    exit /b 1
)

echo [INFO] Building project...
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo [ERROR] Build failed.
    pause
    exit /b 1
)

echo [INFO] Starting application...
java -jar target/truck_project.jar

endlocal
