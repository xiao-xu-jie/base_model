package com.xujie.wx.utils;

import cn.hutool.json.JSONUtil;
import com.xujie.wx.config.WxAppConfig;
import com.xujie.wx.constants.WxAppApiConstant;
import com.xujie.wx.constants.WxMpApiConstant;
import com.xujie.wx.entity.WxAppInfo;
import com.xujie.wx.entity.WxMpToken;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Xujie
 * @since 2024/9/15 10:18 Description: 微信小程序工具类
 */
@Slf4j
@ConditionalOnBean(value = {WxAppConfig.class})
@Component
public class WxAppUtil {
  @Lazy @Resource private WxAppConfig wxAppConfig;

  @Lazy @Resource private WebClient webClient;

  private WxMpToken token;

  public WxAppInfo getWxAppInfo(String code) {
    String appId = wxAppConfig.getAppId();
    String appSecret = wxAppConfig.getAppSecret();
    String url = WxAppApiConstant.WX_OPENID_URL;
    String res =
        webClient
            .get()
            .uri(String.format(url, appId, appSecret, code))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    return JSONUtil.toBean(res, WxAppInfo.class);
  }

  /**
   * 获取Token
   *
   * @return
   */
  public WxMpToken getToken() {
    String url = WxMpApiConstant.GET_TOKEN_API;
    WxMpToken token = null;
    try {
      token =
          webClient
              .get()
              .uri(String.format(url, wxAppConfig.getAppId(), wxAppConfig.getAppSecret()))
              .retrieve()
              .bodyToMono(WxMpToken.class)
              .block();
    } catch (Exception e) {
      log.error("WxMpUtil.getToken.error: {}", e.getMessage());
    }

    if (token != null && ObjectUtils.anyNotNull(token.getErrorCode(), token.getErrorMsg())) {
      log.error("WxMpUtil.getToken.error: {}", token);
    }
    if (token != null) {
      this.token = token;
    }
    return token;
  }
}
