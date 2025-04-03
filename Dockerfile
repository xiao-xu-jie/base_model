# 运行时环境
FROM docker.xxce.cn/xiaoxu-docker/openjdk:17

WORKDIR /app

# 创建目录
RUN mkdir -p /app/logs /app/data /app/lib

# 复制构建产物
COPY  /business/target/xujie-business.jar /app/
COPY  /business/target/lib/ /app/lib/

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8899 \
    REGISTER_PORT=${SERVER_PORT} \
    SERVER_IP=127.0.0.1 \
    JVM_OPTS="-Xms2048M -Xmx2048M -Xss512K -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -Xlog:gc*:file=/app/logs/gc.log:time,level,tags"

# 暴露端口
EXPOSE ${SERVER_PORT}

# 设置启动命令
CMD java -Dloader.path=./lib \
    -Dspring.cloud.nacos.discovery.ip=${SERVER_IP} \
    -Dspring.cloud.nacos.discovery.port=${REGISTER_PORT} \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    -Dserver.port=${SERVER_PORT} \
    ${JVM_OPTS} \
    -jar xujie-business.jar
