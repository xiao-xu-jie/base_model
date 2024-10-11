package com.xujie.wx.constants;

import lombok.Data;

/**
 * @author Xujie
 * @since 2024/9/15 13:07
 */
@Data
public class WxAppApiConstant {
  public static final String WX_OPENID_URL =
      "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
  public static final String C = "https://api.weixin.qq.com/cv/ocr/idcard?access_token=%s";
}
