package com.xujie.business;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * businessApplication
 *
 * @author Xujie
 * @since 2024/9/13 17:24
 **/
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class businessApplication {

    public static String staticEnv;
    public static void main(String[] args) {

        log.info("businessApplication === start");
        SpringApplication.run(businessApplication.class, args);
        log.info("businessApplication === end");


    }

}
