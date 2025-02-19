package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xujie.business.DTO.req.OrderCreateReqDTO;
import com.xujie.business.DTO.res.OrderInfoResDTO;
import com.xujie.business.common.entity.Result;
import com.xujie.business.domain.service.OrderDomainService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器
 *
 * @author Xujie
 * @since 2024/9/27 14:06
 */
@SaIgnore
@RestController
@RequestMapping("/order")
public class OrderController {

  @Resource private OrderDomainService orderDomainService;

  /**
   * 提交订单
   *
   * @return 支付信息
   */
  @PostMapping("/create")
  public Result<OrderInfoResDTO> submit(
      @RequestBody @Validated OrderCreateReqDTO orderCreateReqDTO) {
    String classJson = JSONUtil.toJsonStr(orderCreateReqDTO.getClassList());
    Integer num = orderCreateReqDTO.getClassList().size();
    JSONObject response =
        orderDomainService.createOrder(
            orderCreateReqDTO.getGoodId(),
            orderCreateReqDTO.getUser(),
            orderCreateReqDTO.getPass(),
            classJson,
            num);

    return Result.ok(
        OrderInfoResDTO.builder()
            .url(response.getJSONArray("url").get(0).toString())
            .urlQrcode(response.getJSONArray("url_qrcode").get(0).toString())
            .orderNo(response.getJSONArray("orderNo").get(0).toString())
            .build());
  }

  /**
   * 查询订单状态
   *
   * @param orderNo 订单号
   * @return 订单状态
   */
  @GetMapping("/status")
  public Result<Boolean> status(@RequestParam(value = "orderNo", required = true) Long orderNo) {
    Boolean status = orderDomainService.getOrderStatus(orderNo);
    return Result.ok(status);
  }
}
