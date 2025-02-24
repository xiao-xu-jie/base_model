package com.xujie.business.domain.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xujie.business.application.es.DTO.OrderStatusEsDTO;
import com.xujie.business.application.es.repository.OrderStatusRepository;
import com.xujie.business.application.pay.PayService;
import com.xujie.business.application.pay.entity.OrderRequest;
import com.xujie.business.common.adapters.impl.HttpWebclientAdapterImpl;
import com.xujie.business.common.annotations.MyCache;
import com.xujie.business.common.enums.OrderStatusEnum;
import com.xujie.business.common.enums.SubmitStatusEnum;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryOrderStatusRequest;
import com.xujie.business.common.templates.twoNinePlatform.TwoNineTemplate;
import com.xujie.business.domain.service.OrderDomainService;
import com.xujie.business.infra.DO.BizGood;
import com.xujie.business.infra.DO.BizOrder;
import com.xujie.business.infra.DO.BizSourceStation;
import com.xujie.business.infra.mapper.BizOrderMapper;
import com.xujie.business.infra.service.CategoryGoodService;
import com.xujie.business.infra.service.SourceStationService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 订单领域服务实现类
 *
 * @author Xujie
 * @since 2024/9/27 16:07
 */
@Slf4j
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
    @Resource
    private CategoryGoodService categoryGoodService;
    @Resource
    private PayService payService;
    @Resource
    private HttpWebclientAdapterImpl httpAdapter;
    @Resource
    private BizOrderMapper bizOrderMapper;
    @Resource
    private SourceStationService sourceStationService;
    @Resource
    private TwoNineTemplate platformTemplate;
    @Resource
    private OrderStatusRepository orderStatusRepository;

    @Override
//  @MyCache(key = "order:xxt", expire = 1, timeUnit = TimeUnit.MINUTES)
    @Transactional(rollbackFor = Exception.class)
    public JSONObject createOrder(
            Long goodId, String user, String pass, String classJson, Integer num) {
        log.info("goodId: {}, user: {}, pass: {}, classJson: {}", goodId, user, pass, classJson);
        List<BizGood> entity =
                categoryGoodService.getGoodListByEntity(BizGood.builder().id(goodId).build());
        ConditionCheck.trueAndThrow(ObjectUtils.isEmpty(entity), new CustomException("商品不存在"));
        BizGood bizGood = entity.get(0);
        BigDecimal totalPrice = BigDecimal.ONE;
        totalPrice = totalPrice.multiply(bizGood.getGoodPrice()).multiply(BigDecimal.valueOf(num));
        log.info("totalPrice: {}", totalPrice);
        // 调用API创建微信订单
        String orderId = IdUtil.getSnowflake(1, 1).nextId() + "";
        JSONObject huPiJiaoOrder = createHuPiJiaoOrder(orderId, user, bizGood, num, totalPrice);
        saveOrder(Long.valueOf(orderId), bizGood, user, pass, classJson, num, totalPrice);
        return huPiJiaoOrder;
    }

    private JSONObject createHuPiJiaoOrder(
            String orderId, String user, BizGood bizGood, Integer num, BigDecimal totalPrice) {
        StringBuilder sb = new StringBuilder();
        sb.append(user).append("购买").append(bizGood.getGoodName()).append("x").append(num);
        OrderRequest request =
                OrderRequest.builder()
                        .orderId(orderId)
                        .title(bizGood.getGoodName() + "x" + num)
                        .remark(sb.toString())
                        .totalFee(totalPrice.doubleValue())
                        .build();
        JSONObject order = payService.createOrder(request);
        JSONObject response = new JSONObject();
        response
                .append("url_qrcode", order.get("url_qrcode"))
                .append("orderNo", orderId)
                .append("url", order.get("url"));
        return response;
    }

    @Override
    public Boolean getOrderStatus(Long orderNo) {
        LambdaQueryWrapper<BizOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizOrder::getOrderNo, orderNo);
        BizOrder order = bizOrderMapper.selectOne(queryWrapper);
        ConditionCheck.nullAndThrow(order, new CustomException("订单不存在"));
        return order.getOrderStatus().equals(OrderStatusEnum.PAID);
    }

    @MyCache(key = "order:status", expire = 5, timeUnit = TimeUnit.MINUTES)
    @Override
    public JSONArray queryUserOrders(String phone) {
        // 查询所有订单
//        List<BizOrder> orders = getInProgressOrderByPhone(phone);
//        List<QueryOrderStatusRequest> queryOrderStatusRequests = getQueryOrderStatusRequests(orders);
        List<OrderStatusEsDTO> byUser = orderStatusRepository.findByUser(phone);
        return new JSONArray(byUser);
    }

    private static List<QueryOrderStatusRequest> getQueryOrderStatusRequests(List<BizOrder> orders) {
        return orders.stream()
                .map(order -> QueryOrderStatusRequest.builder()
                        .platform(order.getPlatformId())
                        .platformUid(order.getPlatformUid())
                        .phone(order.getPhone())
                        .password(order.getPassword())
                        .url(order.getStationUrl())
                        .build())
                .toList();
    }

    private List<BizOrder> getInProgressOrderByPhone(String phone) {
        LambdaQueryWrapper<BizOrder> lambdaQueryWrapper = Wrappers.<BizOrder>lambdaQuery()
                .eq(BizOrder::getOrderStatus, OrderStatusEnum.PAID)
                .and((i) -> {
                    i.eq(BizOrder::getSubmitStatus, SubmitStatusEnum.SUBMIT_SUCCESS)
                            .or()
                            .eq(BizOrder::getSubmitStatus, SubmitStatusEnum.IN_PROGRESS);
                })
                .eq(BizOrder::getPhone, phone)
                .isNotNull(BizOrder::getPlatformUid)
                .isNotNull(BizOrder::getStationUrl)
                .isNotNull(BizOrder::getPlatformId);
        return bizOrderMapper.selectList(lambdaQueryWrapper);
    }

    public List<BizOrder> getAllInProgressOrders() {
        LambdaQueryWrapper<BizOrder> lambdaQueryWrapper = Wrappers.<BizOrder>lambdaQuery()
                .eq(BizOrder::getOrderStatus, OrderStatusEnum.PAID)
                .and((i) -> {
                    i.eq(BizOrder::getSubmitStatus, SubmitStatusEnum.SUBMIT_SUCCESS)
                            .or()
                            .eq(BizOrder::getSubmitStatus, SubmitStatusEnum.IN_PROGRESS);
                })
                .isNotNull(BizOrder::getPlatformUid)
                .isNotNull(BizOrder::getStationUrl)
                .isNotNull(BizOrder::getPlatformId);
        return bizOrderMapper.selectList(lambdaQueryWrapper);
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
        BizSourceStation sourceStation = sourceStationService.getSourceStationById(bizGood.getStationId());
        BizOrder order =
                BizOrder.builder()
                        .orderNo(orderNo)
                        .goodName(bizGood.getGoodName())
                        .goodId(bizGood.getId())
                        .classInfo(classJson)
                        .totalPrice(totalPrice)
                        .goodDesc(bizGood.getGoodDesc())
                        .school("school")
                        .platformId(bizGood.getPlatformId())
                        .platformUid(sourceStation.getUid())
                        .stationUrl(sourceStation.getStationUrl())
                        .phone(user)
                        .password(pass)
                        .orderStatus(OrderStatusEnum.UNPAID)
                        .build();
        bizOrderMapper.insert(order);
        return orderNo;
    }
}
