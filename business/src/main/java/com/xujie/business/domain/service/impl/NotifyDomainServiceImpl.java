package com.xujie.business.domain.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xujie.business.common.adapters.impl.PlatForm29AdapterImpl;
import com.xujie.business.common.constants.PlantApiConstant;
import com.xujie.business.common.enums.GoodStatusEnum;
import com.xujie.business.common.enums.OrderStatusEnum;
import com.xujie.business.common.enums.SubmitStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.templates.PlatformTemplate;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.SubmitOrderRequest;
import com.xujie.business.common.templates.twoNinePlatform.DTO.response.SubmitOrderResponse;
import com.xujie.business.domain.service.NotifyDomainService;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.DO.BizOrder;
import com.xujie.business.infra.DO.BizSourceStation;
import com.xujie.business.infra.service.CategoryGoodService;
import com.xujie.business.infra.service.OrderService;
import com.xujie.business.infra.service.SourceStationService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 通知领域服务实现类
 *
 * @author Xujie
 * @since 2024/9/27 16:53
 */
@Slf4j
@Service
public class NotifyDomainServiceImpl implements NotifyDomainService {
  @Resource private PlatForm29AdapterImpl platForm29Adapter;
  @Resource private OrderService orderService;
  @Resource private CategoryGoodService categoryGoodService;
  @Resource private SourceStationService sourceStationService;
  @Resource private PlatformTemplate<SubmitOrderRequest, SubmitOrderResponse> platformTemplate;

  @Override
  @Async
  public void handlePaySuccess(Long orderNo) {
    orderService.getOrderListByEntity(BizOrder.builder().orderNo(orderNo).build()).stream()
        .findFirst()
        .ifPresent(
            order -> {
              if (ObjectUtils.compare(order.getOrderStatus(), OrderStatusEnum.PAID) == 0) {
                log.info("[支付回调]订单已支付，请勿重复支付，订单号：{}", orderNo);
                return;
              }
              order.setOrderStatus(OrderStatusEnum.PAID);
              order.setPayTime(DateUtil.date());
              JSONArray errorData = submitOrder(order, order.getClassInfo());
              order.setErrorData(errorData.toString());
              if (!errorData.isEmpty()) {
                log.error("[支付回调]订单提交失败数据：{}", errorData);
                order.setSubmitStatus(SubmitStatusEnum.SUBMIT_RETRY);
              } else {
                order.setSubmitStatus(SubmitStatusEnum.SUBMIT_SUCCESS);
              }
              orderService.updateOrder(order);
              log.info("[支付回调]订单支付成功，订单号：{}", orderNo);
            });
  }

  public void reSubmitOrder(List<BizOrder> orderList) {
    orderList.forEach(
        order -> {
          JSONArray errorData = submitOrder(order, order.getErrorData());
          if (!errorData.isEmpty()) {
            log.error("[重新提交]订单重新提交失败数据：{}", errorData.toString());
            order.setSubmitStatus(SubmitStatusEnum.SUBMIT_FAIL);
            order.setErrorData(errorData.toString());
          } else {
            order.setSubmitStatus(SubmitStatusEnum.SUBMIT_SUCCESS);
          }
          orderService.updateOrder(order);
        });
  }

  /**
   * 提交课程
   *
   * @param order
   * @param classInfo
   * @return 返回提交失败的课程列表
   */
  private JSONArray submitOrder(BizOrder order, String classInfo) {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("user", order.getPhone());
    map.add("pass", order.getPassword());
    JSONArray jsonArray = JSONUtil.parseArray(classInfo);
    if (jsonArray.isEmpty()) {
      log.error("课程信息为空，订单号：{}", order.getOrderNo());
      return jsonArray;
    }
    JSONArray errorArray = new JSONArray();
    map.add("school", "school");
    List<BizGood> goodListByEntity =
        categoryGoodService.getGoodListByEntity(
            BizGood.builder().id(order.getGoodId()).goodStatus(GoodStatusEnum.UP).build());
    if (ObjectUtils.isNotEmpty(goodListByEntity)) {
      BizGood bizGood =
          goodListByEntity.stream().findFirst().orElseThrow(() -> new CustomException("商品不存在"));
      BizSourceStation sourceStation = getBizSourceStation(bizGood, map);
      map.add("uid", sourceStation.getUid());
      map.add("key", sourceStation.getSecret());
      map.add("url", sourceStation.getStationUrl());
      SubmitOrderRequest baseInfo =
          SubmitOrderRequest.builder()
              .uid(sourceStation.getUid())
              .key(sourceStation.getSecret())
              .url(sourceStation.getStationUrl() + PlantApiConstant.SUBMIT_CLASS_SUFFIX)
              .platform(bizGood.getPlatformId())
              .user(order.getPhone())
              .pass(order.getPassword())
              .school("无")
              .build();
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        setClassInfo(jsonObject, baseInfo);
        try {
          platformTemplate.SubmitOrder(baseInfo);
        } catch (CustomException e) {
          errorArray.add(jsonArray.getJSONObject(i));
        }
      }

      //      submitAndCollectFailClass(jsonArray, map, errorArray);
      return errorArray;
    } else {
      log.error("订单回调：商品不存在，订单号：{}", order.getOrderNo());
      return jsonArray;
    }
  }

  private static void setClassInfo(JSONObject jsonObject, SubmitOrderRequest baseInfo) {
    String name = jsonObject.getStr("name");
    String id = jsonObject.getStr("id", "");
    baseInfo.setKcname(name);
    baseInfo.setKcid(id);
  }

  /**
   * 提交订单并收集失败课程
   *
   * @param jsonArray
   * @param map
   * @param errorArray
   */
  private void submitAndCollectFailClass(
      JSONArray jsonArray, MultiValueMap<String, String> map, JSONArray errorArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      map.add("kcname", jsonArray.getJSONObject(i).getStr("name"));
      if (jsonArray.getJSONObject(i).getStrEscaped("id", null) != null) {
        map.add("kcid", jsonArray.getJSONObject(i).getStr("id"));
      }
      try {
        platForm29Adapter.submitOrder(map);
      } catch (CustomException e) {
        log.error("订单提交异常：{}", e.getMessage());
        errorArray.add(jsonArray.getJSONObject(i));
      }
      map.remove("kcname");
      map.remove("kcid");
    }
  }

  /**
   * 获取货源站
   *
   * @param bizGood
   * @param map
   * @return
   */
  private BizSourceStation getBizSourceStation(BizGood bizGood, MultiValueMap<String, String> map) {

    // 查询货源站
    Long stationId = bizGood.getStationId();
    List<BizSourceStation> sourceStationListByEntity =
        sourceStationService.getSourceStationListByEntity(
            BizSourceStation.builder().id(stationId).build());
    BizSourceStation sourceStation =
        sourceStationListByEntity.stream()
            .findFirst()
            .orElseThrow(() -> new CustomException("货源站不存在"));
    return sourceStation;
  }
}
