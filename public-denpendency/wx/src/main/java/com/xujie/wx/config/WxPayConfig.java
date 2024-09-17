package com.xujie.wx.config;

/**
 * @author Xujie
 * @since 2024/9/17 10:35
 **/


import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

/**
 * @Author: Xujie
 * @Date: 2024/7/20 10:13
 * @Description: 微信支付配置
 **/
@Configuration
@Getter
public class WxPayConfig {

    public NotificationParser parser;
    @Value("${wx.pay.appId}")
    private String appId;
    @Value("${wx.pay.mchId}")
    private String merchantId;
    @Value("${wx.pay.keyPath}")
    private String privateKeyPath;
    @Value("${wx.pay.merchantSerialNumber}")
    private String merchantSerialNumber;
    @Value("${wx.pay.apiV3Key}")
    private String apiV3Key;
    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;
    @Value("${wx.pay.privateKey}")
    private String privateKey;


    @Bean
    public RSAAutoCertificateConfig config() {
        return new RSAAutoCertificateConfig.Builder()
                .merchantId(merchantId)
                .privateKey(privateKey)
                .merchantSerialNumber(merchantSerialNumber)
                .apiV3Key(apiV3Key)
                .build();
    }

    @Bean
    public JsapiServiceExtension jsapiServiceExtension(Config config) {

        return new JsapiServiceExtension.Builder()
                .config(config)
                .build();
    }

    //解密报文
    @Bean("notificationParser")
    public NotificationParser getNotificationConfig(RSAAutoCertificateConfig config) {
        if (ObjectUtils.isEmpty(config)) {
            config = new RSAAutoCertificateConfig.Builder()
                    .merchantId(merchantId)
                    .privateKey(privateKey)
                    .merchantSerialNumber(merchantSerialNumber)
                    .apiV3Key(apiV3Key)
                    .build();
        }
        if (ObjectUtils.isEmpty(parser)) {
            parser = new NotificationParser(config);
        }
        return parser;
    }

}

