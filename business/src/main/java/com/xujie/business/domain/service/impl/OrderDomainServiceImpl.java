package com.xujie.business.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xujie.business.DTO.req.WxOrderCreateReqDTO;
import com.xujie.business.common.adapters.impl.HttpWebclientAdapterImpl;
import com.xujie.business.common.annotations.MyCache;
import com.xujie.business.common.entity.Result;
import com.xujie.business.common.enums.OrderStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.config.SiteConfig;
import com.xujie.business.domain.service.OrderDomainService;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.DO.BizOrder;
import com.xujie.business.infra.mapper.BizOrderMapper;
import com.xujie.business.infra.service.CategoryGoodService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * 订单领域服务实现类
 *
 * @author Xujie
 * @since 2024/9/27 16:07
 */
@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
  @Resource private CategoryGoodService categoryGoodService;
  @Resource private HttpWebclientAdapterImpl httpAdapter;
  @Resource private BizOrderMapper bizOrderMapper;
  @Resource private SiteConfig siteConfig;

  @Override
  @MyCache(key = "order:xxt", expire = 15, timeUnit = TimeUnit.MINUTES)
  public Long createOrder(Long goodId, String user, String pass, String classJson, Integer num) {
    log.info("goodId: {}, user: {}, pass: {}, classJson: {}", goodId, user, pass, classJson);
    List<BizGood> entity =
        categoryGoodService.getGoodListByEntity(BizGood.builder().id(goodId).build());
    ConditionCheck.trueAndThrow(ObjectUtils.isEmpty(entity), new CustomException("商品不存在"));
    BizGood bizGood = entity.get(0);
    BigDecimal totalPrice = BigDecimal.ONE;
    totalPrice =
        totalPrice
            .multiply(bizGood.getGoodPrice())
            .multiply(BigDecimal.valueOf(num))
            .multiply(BigDecimal.valueOf(100));
    log.info("totalPrice: {}", totalPrice);
    // 调用API创建微信订单
    WxOrderCreateReqDTO createReqDTO =
        WxOrderCreateReqDTO.builder()
            .goodName(bizGood.getGoodName())
            .price(totalPrice.intValue())
            .orderDesc("用户：" + user + "，商品ID：" + goodId + "，购买数量：" + num)
            .siteId(Long.valueOf(siteConfig.getId()))
            .siteKey(siteConfig.getKey())
            .build();
    Result<String> order = null;
    try {
      order = httpAdapter.createOrder(createReqDTO);
    } catch (Exception e) {
      throw new CustomException("创建订单失败");
    }
    ConditionCheck.trueAndThrow(
        order == null || order.getData() == null, new CustomException("创建订单失败"));

    return saveOrder(
        Long.valueOf(order.getData()), bizGood, user, pass, classJson, num, totalPrice);
  }

  @Override
  public Boolean getOrderStatus(Long orderNo) {
    LambdaQueryWrapper<BizOrder> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(BizOrder::getOrderNo, orderNo);
    BizOrder order = bizOrderMapper.selectOne(queryWrapper);
    ConditionCheck.nullAndThrow(order, new CustomException("订单不存在"));
    return order.getOrderStatus().equals(OrderStatusEnum.PAID);
  }

  private Long saveOrder(
      Long orderNo,
      BizGood bizGood,
      String user,
      String pass,
      String classJson,
      Integer num,
      BigDecimal totalPrice) {
    // 保存订单信息
    BizOrder order =
        BizOrder.builder()
            .orderNo(orderNo)
            .goodName(bizGood.getGoodName())
            .goodId(bizGood.getId())
            .classInfo(classJson)
            .totalPrice(totalPrice)
            .goodDesc(bizGood.getGoodDesc())
            .school("school")
            .phone(user)
            .password(pass)
            .orderStatus(OrderStatusEnum.UNPAID)
            .build();
    bizOrderMapper.insert(order);
    return orderNo;
  }
}
