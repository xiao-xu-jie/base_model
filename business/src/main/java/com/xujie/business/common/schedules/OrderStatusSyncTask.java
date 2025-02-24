package com.xujie.business.common.schedules;

import cn.hutool.json.JSONArray;
import com.xujie.business.application.es.DTO.OrderStatusEsDTO;
import com.xujie.business.application.es.repository.OrderStatusRepository;
import com.xujie.business.common.templates.twoNinePlatform.DTO.request.QueryOrderStatusRequest;
import com.xujie.business.common.templates.twoNinePlatform.TwoNineTemplate;
import com.xujie.business.domain.service.OrderDomainService;
import com.xujie.business.infra.DO.BizOrder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xujie
 * @since 2025/2/24 13:17
 **/
@Slf4j
@Component
public class OrderStatusSyncTask {
    @Resource
    private OrderDomainService orderDomainService;
    @Resource
    private TwoNineTemplate twoNineTemplate;
    @Resource
    private OrderStatusRepository orderStatusRepository;

    @Scheduled(cron = " 0 0/1 * * * *")
    public void syncOrderStatus() {
        // 获取所有待同步状态的订单
        List<BizOrder> allInProgressOrders = orderDomainService.getAllInProgressOrders();
        log.info("[订单状态同步] 待同步订单：{}", allInProgressOrders.size());
        // 获取所有订单的同步状态
        List<QueryOrderStatusRequest> queryOrderStatusRequests = allInProgressOrders.stream()
                .map(order -> QueryOrderStatusRequest.builder()
                        .platform(order.getPlatformId())
                        .platformUid(order.getPlatformUid())
                        .phone(order.getPhone())
                        .password(order.getPassword())
                        .url(order.getStationUrl())
                        .build())
                .toList();
        JSONArray objects = twoNineTemplate.queryOrderStatusList(queryOrderStatusRequests);
        List<OrderStatusEsDTO> list = null;
        try {
            list = objects.toList(OrderStatusEsDTO.class);
        } catch (Exception e) {
            log.error("JSON 反序列化错误：{}，{}", e.getMessage(), objects);
        }
        log.info("[订单状态同步] 获取所有待同步订单状态：{}", list);

        // 保存到ES
        orderStatusRepository.saveAll(list);
    }
}
