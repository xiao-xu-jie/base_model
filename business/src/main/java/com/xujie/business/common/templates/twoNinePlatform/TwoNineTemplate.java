package com.xujie.business.common.templates.twoNinePlatform;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xujie.business.common.constants.PlantApiConstant;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.templates.PlatformTemplate;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryOrderStatusRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryUserClassRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.SubmitOrderRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.response.SubmitOrderResponse;
import com.xujie.business.common.utils.WebclientUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Xujie
 * @since 2024/11/15 00:05
 */
@Slf4j
@Component
public class TwoNineTemplate
        extends PlatformTemplate<SubmitOrderRequest, SubmitOrderResponse, QueryUserClassRequest, QueryOrderStatusRequest> {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    protected boolean beforeCheck(SubmitOrderRequest response) {
        return true;
    }

    @Override
    protected boolean afterSubmitCheck(SubmitOrderResponse response) {
        Integer code = Optional.ofNullable(response).map(SubmitOrderResponse::getCode).orElse(1);
        if (log.isInfoEnabled()) {

            log.debug("[TwoNineTemplate]提交订单返回信息：{}", JSONUtil.parse(response).toJSONString(4));
        }
        return code == 0;
    }

    @Override
    protected boolean afterQueryCheck(String response) {
        try {
            JSONObject json = new JSONObject(response);
            Integer code = json.getInt("code", -1);
            log.error("[afterQueryCheck]查课检测信息：{}", json);
            return code == 0 || code == 1;
        } catch (Exception e) {
            log.error("[afterQueryCheck]查询检测异常：{}", e.getMessage());
        }
        return true;
    }

    @Override
    protected void handleSubmitFail(SubmitOrderRequest info) {
        if (log.isInfoEnabled()) {

            log.debug("[TwoNineTemplate]提交订单失败，订单信息：{}", JSONUtil.parse(info).toString());
            throw new CustomException("[29平台]29平台提交订单失败");
        }
    }

    @Override
    protected void handleQueryFail(String info) {
        JSONObject json = new JSONObject(info);
        throw new CustomException(json.getStr("msg", "查询失败，可能是系统繁忙，请稍后再试"));
    }

    @Override
    protected void handleSuccess(SubmitOrderRequest info) {
        if (log.isInfoEnabled()) {
            log.debug("[TwoNineTemplate]提交订单成功，订单信息：{}", JSONUtil.parse(info).toString());
        }
    }

    @Override
    protected SubmitOrderResponse submit(SubmitOrderRequest order) {
        JSONObject requestBody = new JSONObject(order);
        MultiValueMap<String, String> map = buildRequestMap(requestBody);
        return WebclientUtil.post(
                order.getUrl(), map, MediaType.APPLICATION_FORM_URLENCODED, SubmitOrderResponse.class, 10);
    }

    private MultiValueMap<String, String> buildRequestMap(JSONObject requestBody) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        requestBody.forEach((k, v) -> map.add(k, v.toString()));
        map.remove("url");
        return map;
    }

    @Override
    protected <T> String query(QueryUserClassRequest order) {

        return WebclientUtil.post(
                order.getUrl(), order.getData(), MediaType.APPLICATION_FORM_URLENCODED, String.class, 100);
    }

    /**
     * 查询用户订单状态
     *
     * @param order
     * @return
     */
    @Override
    protected JSONObject queryUserOrderStatus(QueryOrderStatusRequest order) {
        MultiValueMap<String, String> mapVal = order.getMapVal();
        return WebclientUtil.post(order.getUrl(), mapVal, MediaType.APPLICATION_FORM_URLENCODED, JSONObject.class, 500);
    }


    public JSONArray queryOrderStatusList(List<QueryOrderStatusRequest> order) {
        JSONArray jsonArray = new JSONArray();
        List<CompletableFuture<Void>> futures = new ArrayList<>(order.size());
        order.forEach(item -> {
            MultiValueMap<String, String> mapVal = item.getMapVal();
            CompletableFuture<Void> postAsync = WebclientUtil
                    .postAsync(item.getUrl() + PlantApiConstant.QUERY_ORDER_SUFFIX, mapVal, MediaType.APPLICATION_FORM_URLENCODED, String.class, 500, taskExecutor)
                    .thenAccept((result) -> {
                        if (result == null || !JSONUtil.isTypeJSON(result)) return;
                        // 序列化
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.isEmpty() ||  jsonObject.getInt("code") != 1) return;
                        addToJsonArray(jsonArray, jsonObject);
                    }).exceptionally(e -> {
                        log.error("订单状态同步发送异常",e);
                        return null;
                    });
            futures.add(postAsync);
        });
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        voidCompletableFuture.join();
        return jsonArray;
    }

    private void addToJsonArray(JSONArray array, JSONObject jsonObject) {
        if (array == null) return;
        synchronized (array) {
            array.addAll(jsonObject.getJSONArray("data"));
        }
    }
}
