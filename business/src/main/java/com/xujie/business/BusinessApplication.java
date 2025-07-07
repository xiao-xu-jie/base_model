package com.xujie.business;

import com.xujie.business.application.sms.config.SmsConfiguration;
import com.xujie.business.application.weixin.configuration.WxAppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * businessApplication
 *
 * @author Xujie
 * @since 2024/9/13 17:24
 **/
@Slf4j
@ComponentScan(basePackages = {"com.xujie.business", "com.xujie.future"})
@EnableConfigurationProperties({WxAppConfiguration.class, SmsConfiguration.class})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BusinessApplication {

    public static void main(String[] args) {

        log.info("businessApplication === start");
        SpringApplication.run(BusinessApplication.class, args);
        log.info("businessApplication === end");


    }

}
