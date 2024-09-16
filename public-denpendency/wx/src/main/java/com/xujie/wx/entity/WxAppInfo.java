package com.xujie.wx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Xujie
 * @Date: 2024/7/20 13:55
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxAppInfo {
    @JsonProperty("unionid")
    private String unionid;
    @JsonProperty("openid")
    private String openid;
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("errcode")
    private String errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}
