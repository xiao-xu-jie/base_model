package com.xujie;

import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Xujie
 * @since 2024/9/13 18:00
 **/
@Slf4j
@MapperScan("com.xujie.manager.infra.mapper")
@EnableCaching
@EnableAspectJAutoProxy
@EnableFileStorage
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        log.info("ManagerApplication start...");
        SpringApplication.run(ManagerApplication.class, args);
        log.info("ManagerApplication start success...");
    }
}
