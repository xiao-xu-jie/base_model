package com.xujie.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * businessApplication
 *
 * @author Xujie
 * @since 2024/9/13 17:24
 **/
@EnableDiscoveryClient
@MapperScan("com.xujie.business.*.mapper")
@SpringBootApplication
public class businessApplication {

    public static String staticEnv;

    public static void main(String[] args) {

        SpringApplication.run(businessApplication.class, args);


    }

}
