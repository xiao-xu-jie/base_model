package com.xujie.wx.constants;

import lombok.Data;

/**
 * @author Xujie
 */
@Data
public class WxMpApiConstant {
    /**
     * 获取token
     */
    public static final String GET_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 获取二维码
     */
    public static final String GET_QRCODE_API = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    /**
     * 展示二维码
     */
    public static final String SHOW_QRCODE_API = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    /**
     * 发送模板
     */
    public static final String SEND_TEMPLATE_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
}
