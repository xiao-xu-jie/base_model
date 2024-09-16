package com.xujie.wx.utils;

import com.xujie.wx.config.WxAppConfig;
import com.xujie.wx.constants.WxAppApiConstant;
import com.xujie.wx.entity.WxAppInfo;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Xujie
 * @since 2024/9/15 10:18
 * Description: 微信小程序工具类
 **/

@Component
public class WxAppUtils {
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
