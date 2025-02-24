package com.xujie.business.common.schedules;

import com.xujie.business.common.enums.SubmitStatusEnum;
import com.xujie.business.domain.service.NotifyDomainService;
import com.xujie.business.infra.DO.BizOrder;
import com.xujie.business.infra.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/10/1 20:25
 */
@Slf4j
@Component
public class OrderResubmitTask {
    @Resource
    private OrderService orderService;
    @Resource
    private NotifyDomainService notifyDomainService;
    private final String traceId = "traceId";

    @Scheduled(cron = "0 0 0/1 * * ? ", zone = "Asia/Shanghai")
    public void orderResubmit() {
        try {
            log.info("====重新提交订单定时任务开始==== {}", DateTime.now());
            BizOrder bizOrder = BizOrder.builder().submitStatus(SubmitStatusEnum.SUBMIT_RETRY).build();
            List<BizOrder> retryList = orderService.getOrderListByEntity(bizOrder);
            log.info("====本次任务数量==== {}", retryList.size());
            // 重新提交逻辑
            if (ObjectUtils.isNotEmpty(retryList)) {
                notifyDomainService.reSubmitOrder(retryList);
            }
            log.info("====重新提交订单定时任务结束===={}", DateTime.now());
        } catch (IllegalArgumentException e) {
            log.error("重新提交订单定时任务异常: {}", e.getMessage());
        }
    }
}
