package com.xujie;

import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.xujie.business.businessApplication;
import com.xujie.wx.config.WxAppConfig;
import com.xujie.wx.config.WxPayConfig;
import com.xujie.wx.entity.WxAppInfo;
import com.xujie.wx.entity.WxMpQrCode;
import com.xujie.wx.entity.WxMpToken;
import com.xujie.wx.utils.WxAppUtil;
import com.xujie.wx.utils.WxMpUtil;
import com.xujie.wx.utils.WxPayUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/9/15 10:24
 **/

@SpringBootTest(classes = {businessApplication.class})
public class TestWx {
    @Resource
    private WxAppConfig wxAppConfig;

    @Resource
    private WxAppUtil wxAppUtil;

    @Resource
    private WxMpUtil wxMpUtil;

    @Resource
    private WxPayUtil wxPayUtil;

    @Resource
    private WxPayConfig wxPayConfig;

    @Test
    public void test() {
        WxAppInfo wxAppInfo = wxAppUtil.getWxAppInfo("123");
        System.out.println(wxAppInfo);
    }

    @Test
    public void test2() {
        WxMpToken token = wxMpUtil.getToken();
        System.out.println(token);
        WxMpQrCode qrCode = wxMpUtil.getQrCode("1234", 60L);
        System.out.println(qrCode);

    }

    @Test
    public void test3() {
        WxPayUtil.OrderBaseInfo build = WxPayUtil
                .OrderBaseInfo
                .builder()
                .orderNo("15182659916")
                .orderDesc("test")
                .openId("onEzD5fpsB8CaYiSQ4BAhI2V9u04")
                .orderPrice(10)
                .build();
        PrepayWithRequestPaymentResponse order = wxPayUtil.createOrder(build);
        System.out.println(wxPayConfig.getAppId());
    }
}
