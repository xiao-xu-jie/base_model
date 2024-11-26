package com.xujie.business.common.templates.twoNinePlatform;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.templates.PlatformTemplate;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryUserClassRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.SubmitOrderRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.response.SubmitOrderResponse;
import com.xujie.business.common.utils.WebclientUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Xujie
 * @since 2024/11/15 00:05
 */
@Slf4j
@Component
public class TwoNineTemplate
    extends PlatformTemplate<SubmitOrderRequest, SubmitOrderResponse, QueryUserClassRequest> {
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
      return code == 0 || code == 1;
    } catch (Exception e) {
      log.error("[afterQueryCheck]查询后检测失败：{}", e.getMessage());
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
    throw new CustomException("查询失败，可能是系统繁忙，请稍后再试");
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
        order.getUrl(), order.getData(), MediaType.APPLICATION_FORM_URLENCODED, String.class, 10);
  }

  public static void main(String[] args) {
    TwoNineTemplate twoNineTemplate = new TwoNineTemplate();
    SubmitOrderRequest build =
        SubmitOrderRequest.builder()
            .url("http://www.wbaidu.com")
            .key("key")
            .platform(1)
            .user("12345678901")
            .pass("123456")
            .kcname("课程名称")
            .kcid("课程ID")
            .build();
    twoNineTemplate.SubmitOrder(build);
  }
}
