package com.xujie.wx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxMpToken {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("errcode")
    private String errorCode;
    @JsonProperty("errmsg")
    private String errorMsg;
}
