package com.xujie.business.domain.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.xujie.business.DTO.res.SubmitOrderResDTO;
import com.xujie.business.common.adapters.impl.PlatForm29AdapterImpl;
import com.xujie.business.common.enums.OrderStatusEnum;
import com.xujie.business.common.exception.CustomException;
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

  @Override
  @Async
  public void handlePaySuccess(Long orderNo) {
    orderService.getOrderListByEntity(BizOrder.builder().orderNo(orderNo).build()).stream()
        .findFirst()
        .ifPresent(
            order -> {
              if (order.getOrderStatus().equals(OrderStatusEnum.PAID)) {
                log.info("订单已支付，订单号：{}", orderNo);
                return;
              }
              order.setOrderStatus(OrderStatusEnum.PAID);
              orderService.updateOrder(order);
              log.info("订单支付成功，订单号：{}", orderNo);
              submitOrder(order);
            });
  }

  private void submitOrder(BizOrder order) {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("user", order.getPhone());
    map.add("pass", order.getPassword());
    String classInfo = order.getClassInfo();
    JSONArray jsonArray = JSONUtil.parseArray(classInfo);

    map.add("school", "school");
    List<BizGood> goodListByEntity =
        categoryGoodService.getGoodListByEntity(BizGood.builder().id(order.getGoodId()).build());
    if (ObjectUtils.isNotEmpty(goodListByEntity)) {
      BizGood bizGood =
          goodListByEntity.stream().findFirst().orElseThrow(() -> new CustomException("商品不存在"));
      map.add("platform", String.valueOf(bizGood.getPlatformId()));
      // 查询货源站
      Long stationId = bizGood.getStationId();
      List<BizSourceStation> sourceStationListByEntity =
          sourceStationService.getSourceStationListByEntity(
              BizSourceStation.builder().id(stationId).build());
      BizSourceStation sourceStation =
          sourceStationListByEntity.stream()
              .findFirst()
              .orElseThrow(() -> new CustomException("货源站不存在"));
      map.add("uid", sourceStation.getUid());
      map.add("key", sourceStation.getKey());
      map.add("url", sourceStation.getStationUrl());
      for (int i = 0; i < jsonArray.size(); i++) {
        map.add("kcname", jsonArray.getJSONObject(i).getStr("name"));
        if (jsonArray.getJSONObject(i).getStrEscaped("id", null) != null) {
          map.add("kcid", jsonArray.getJSONObject(i).getStr("id"));
        }
        SubmitOrderResDTO submitOrderResDTO = platForm29Adapter.submitOrder(map);
        map.remove("kcname");
        map.remove("kcid");
      }

    } else {
      log.error("订单回调：商品不存在，订单号：{}", order.getOrderNo());
      return;
    }
  }
}
