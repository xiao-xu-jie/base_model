package com.xujie.business;

import lombok.extern.slf4j.Slf4j;
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
public class BusinessApplication {

    public static void main(String[] args) {

        log.info("businessApplication === start");
        SpringApplication.run(BusinessApplication.class, args);
        log.info("businessApplication === end");


    }

}
