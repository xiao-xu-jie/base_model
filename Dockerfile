FROM maven:3.8-openjdk-11 AS builder

# 设置工作目录
WORKDIR /build

# 首先复制依赖相关文件，利用Docker缓存机制

COPY public-denpendency ./public-denpendency/


# 构建公共依赖
RUN cd public-denpendency && mvn clean install -DskipTests=true

# 复制业务代码
COPY business ./business/

# 构建业务应用
RUN cd ../business && mvn clean package -DskipTests=true

# 运行时环境
FROM openjdk:17

WORKDIR /app

# 创建目录
RUN mkdir -p /app/logs /app/data /app/lib

# 从构建阶段复制构建产物
COPY --from=builder /build/business/target/xujie-business.jar /app/
COPY --from=builder /build/business/target/lib/ /app/lib/

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=prod \
    SERVER_PORT=8899 \
    JVM_OPTS="-Xms2048M -Xmx2048M -Xss512K -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -Xlog:gc*:file=/app/logs/gc.log:time,level,tags"

# 暴露端口
EXPOSE 8899

# 设置启动命令
CMD java -Dloader.path=./lib \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    -Dserver.port=${SERVER_PORT} \
    ${JVM_OPTS} \
    -jar xujie-business.jar
