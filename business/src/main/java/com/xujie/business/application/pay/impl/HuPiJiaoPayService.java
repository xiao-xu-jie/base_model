package com.xujie.business.application.pay.impl;

import cn.hutool.json.JSONObject;
import com.xujie.business.application.pay.AbstractHuPiJiaoPayService;
import com.xujie.business.application.pay.entity.OrderRequest;
import com.xujie.business.application.pay.entity.RefundRequest;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.config.HuPiJiaoPayConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.Map;

import static com.xujie.business.common.constants.HuPiJiaoPayConstant.*;

@Slf4j
@Service
public class HuPiJiaoPayService extends AbstractHuPiJiaoPayService {
    @Resource
    private WebClient webClient;

    @Resource(name = "huPiJiaoPayConfig")
    private HuPiJiaoPayConfig config;

    private final Integer timeout = 5000;

    @Override
    public JSONObject createOrder(OrderRequest orderRequest) {
        Map<String, Object> requestBody = orderRequest.getReqBody();
        requestBody.put("appid", config.getAppid());
        requestBody.put("version", "1.1");
        requestBody.put("nonce_str", getNonceStr());
        requestBody.put("type", TYPE);
        requestBody.put("wap_name", "小徐网络");
        requestBody.put("wap_url", "xxt.xxce.cn");
        requestBody.put(NOTIFY_URL, config.getNotifyUrl());
        requestBody.put(RETURN_URL, config.getReturnUrl());
        requestBody.put("time", getSecondTimestamp(new Date()));
        String hash = getHash(requestBody, config.getAppSecret());
        requestBody.put("hash", hash);
        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 创建订单请求体：{}", requestBody);
        }
        String post = null;
        try {
            post = post(config.getUrl(), requestBody, webClient, timeout);
        } catch (Exception e) {
            log.error("对接平台创建订单请求异常: ", e);
            throw new CustomException("对接平台创建订单请求异常！");
        }

        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送创建订单请求响应结果：{}", post);
        }
        return getJsonObject(post);
    }

    @Override
    public JSONObject refundOrder(RefundRequest refundRequest) {
        Map<String, Object> requestBody = refundRequest.getRefundMap();
        requestBody.put("appid", config.getAppid());
        requestBody.put("nonce_str", getNonceStr());
        requestBody.put("time", getSecondTimestamp(new Date()));
        String hash = getHash(requestBody, config.getAppSecret());
        requestBody.put("hash", hash);
        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送请求退款请求体：{}", requestBody);
        }
        String post = null;
        try {
            post = post(config.getRefundUrl(), requestBody, webClient, timeout);
        } catch (Exception e) {
            log.error("对接平台退款请求异常: ", e);
            throw new CustomException("对接平台退款请求异常！");
        }

        if (log.isInfoEnabled()) {
            log.info("[HuPiJiaoPayService] 发送订单退款请求响应结果：{}", post);
        }
        return getJsonObject(post);
    }
}
