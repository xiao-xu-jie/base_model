package com.xujie.wx.utils;

import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.xujie.wx.config.WxPayConfig;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Xujie
 * @since 2024/9/17 10:43
 **/
@Slf4j
@ConditionalOnBean(value = {WxPayConfig.class})
@Component
public class WxPayUtil {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private JsapiServiceExtension baseService;

    @Resource(name = "notificationParser")
    private NotificationParser notificationParser;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    public PrepayWithRequestPaymentResponse createOrder(OrderBaseInfo order) {

        //创建预支付订单
        PrepayRequest prepayRequest = new PrepayRequest();
        prepayRequest.setAppid(wxPayConfig.getAppId());
        prepayRequest.setMchid(wxPayConfig.getMerchantId());
        prepayRequest.setOutTradeNo(String.valueOf(order.getOrderNo()));
        prepayRequest.setDescription(order.getOrderDesc());
        prepayRequest.setNotifyUrl(wxPayConfig.getNotifyUrl());

        //设置金额
        Amount amount = new Amount();
        amount.setTotal(order.getOrderPrice());
        prepayRequest.setAmount(amount);

        //设置支付者
        Payer payer = new Payer();
        payer.setOpenid(order.getOpenId());
        prepayRequest.setPayer(payer);
        return baseService.prepayWithRequestPayment(prepayRequest);
    }

    /**
     * 处理支付回调
     *
     * @param request
     * @return
     */
    public Transaction payNotifyHandle(HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("OrderNotifyController.notifyHandle.request:{}", request);
        }
        String body = getRequestBody(request);
        //随机串
        String nonceStr = request.getHeader("Wechatpay-Nonce");
        //微信传递过来的签名
        String signature = request.getHeader("Wechatpay-Signature");
        //证书序列号（微信平台）
        String serialNo = request.getHeader("Wechatpay-Serial");
        //时间戳
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        //加密类型
        String signatureType = request.getHeader("Wechatpay-Signature-Type");

        // 构造 RequestParam
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(serialNo)
                .nonce(nonceStr)
                .signature(signature)
                .timestamp(timestamp)
                .signType(signatureType)// 若未设置signType，默认值为 WECHATPAY2-SHA256-RSA2048
                .body(body)
                .build();

        Transaction transaction = notificationParser.parse(requestParam, Transaction.class);
        if (log.isInfoEnabled()) {
            log.info("OrderNotifyController.notifyHandle.transaction:{}", transaction);
        }
        return transaction;
    }

    /**
     * 读取请求数据流
     *
     * @param request
     * @return
     */
    private String getRequestBody(HttpServletRequest request) {

        StringBuffer sb = new StringBuffer();

        try (ServletInputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            log.error("读取数据流异常:{}", e);
        }
        return sb.toString();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderBaseInfo {
        String orderNo;
        String openId;
        String orderDesc;
        Integer orderPrice;

    }
}
