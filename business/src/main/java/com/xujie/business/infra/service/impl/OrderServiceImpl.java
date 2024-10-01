package com.xujie.business.infra.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.business.infra.DO.BizOrder;
import com.xujie.business.infra.mapper.BizOrderMapper;
import com.xujie.business.infra.service.OrderService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 *
 * @author Xujie
 * @since 2024/9/27 17:07
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
  @Resource private BizOrderMapper bizOrderMapper;

  @Override
  public List<BizOrder> getOrderListByEntity(BizOrder entity) {
    return bizOrderMapper.findByAll(entity);
  }

  @Override
  public void updateOrder(BizOrder order) {
    LambdaQueryWrapper<BizOrder> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(BizOrder::getOrderNo, order.getOrderNo());

    bizOrderMapper.update(
        BizOrder.builder()
            .orderStatus(order.getOrderStatus())
            .payTime(order.getPayTime())
            .errorData(order.getErrorData())
            .submitStatus(order.getSubmitStatus())
            .build(),
        queryWrapper);
  }
}
