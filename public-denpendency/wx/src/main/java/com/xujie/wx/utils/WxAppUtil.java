package com.xujie.wx.utils;

import com.xujie.wx.config.WxAppConfig;
import com.xujie.wx.constants.WxAppApiConstant;
import com.xujie.wx.entity.WxAppInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Xujie
 * @since 2024/9/15 10:18
 * Description: 微信小程序工具类
 **/
@Slf4j
@ConditionalOnBean(value = {WxAppConfig.class})
@Component
public class WxAppUtil {
    @Lazy
    @Resource
    private WxAppConfig wxAppConfig;

    @Lazy
    @Resource
    private WebClient webClient;


    public WxAppInfo getWxAppInfo(String code) {
        String appId = wxAppConfig.getAppId();
        String appSecret = wxAppConfig.getAppSecret();
        String url = WxAppApiConstant.WX_OPENID_URL;
        WxAppInfo wxAppInfo = webClient.get()
                .uri(String.format(url, appId, appSecret, code))
                .retrieve()
                .bodyToMono(WxAppInfo.class)
                .block();
        return wxAppInfo;
    }

}
