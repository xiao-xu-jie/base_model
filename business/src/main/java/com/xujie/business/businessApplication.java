package com.xujie.business;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * businessApplication
 *
 * @author Xujie
 * @since 2024/9/13 17:24
 **/
@Slf4j
@MapperScan("com.xujie.business.*.mapper")
@SpringBootApplication
public class businessApplication {

    public static String staticEnv;

    public static void main(String[] args) {

        log.info("businessApplication === start");
        SpringApplication.run(businessApplication.class, args);
        log.info("businessApplication === end");


    }

}
