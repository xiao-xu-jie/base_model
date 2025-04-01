#!/bin/bash

# 设置项目相关变量
export PROJECT_NAME="xujie-business"
export PROJECT_PATH="${PROJECT_PATH:-/www/wwwroot/java项目/chaoxing}"
export PROJECT_BRANCH="${PROJECT_BRANCH:-master}"
export SERVER_PORT="${SERVER_PORT:-8899}"

# 创建日志目录
mkdir -p ${PROJECT_PATH}/logs

# 记录部署开始
DATE_STR=$(date +"%Y_%m_%d_%H_%M_%S")
LOG_FILE="${PROJECT_PATH}/deploy-${PROJECT_BRANCH}-${DATE_STR}.log"

echo "===================================" | tee -a $LOG_FILE
echo "开始部署 $PROJECT_NAME - $DATE_STR" | tee -a $LOG_FILE

# 停止并移除旧的容器
echo "停止并移除旧的容器..." | tee -a $LOG_FILE
docker-compose down | tee -a $LOG_FILE

# 构建并启动新容器
echo "构建并启动新容器..." | tee -a $LOG_FILE
docker-compose build --no-cache | tee -a $LOG_FILE
docker-compose up -d | tee -a $LOG_FILE

# 检查容器是否成功启动
sleep 10
if [ "$(docker ps -q -f name=$PROJECT_NAME)" ]; then
    echo "$PROJECT_NAME 容器已成功启动" | tee -a $LOG_FILE

    # 显示容器日志
    echo "最近的容器日志:" | tee -a $LOG_FILE
    docker-compose logs --tail=20 | tee -a $LOG_FILE

    # 显示容器信息
    echo "容器信息:" | tee -a $LOG_FILE
    docker inspect $PROJECT_NAME | tee -a $LOG_FILE
else
    echo "容器启动失败，请检查日志" | tee -a $LOG_FILE
    docker-compose logs | tee -a $LOG_FILE
fi

echo "部署过程完成 - $(date)" | tee -a $LOG_FILE
echo "===================================" | tee -a $LOG_FILE
