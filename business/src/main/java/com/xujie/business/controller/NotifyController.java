package com.xujie.business.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xujie.business.common.exception.CustomException;
import com.xujie.business.common.utils.HashUtil;
import com.xujie.business.config.HuPiJiaoPayConfig;
import com.xujie.business.config.SiteConfig;
import com.xujie.business.domain.service.NotifyDomainService;
import com.xujie.tools.ConditionCheck;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 订单回调控制器
 *
 * @author Xujie
 * @since 2024/9/27 16:49
 */
@Slf4j
@SaIgnore
@RestController
public class NotifyController {
    @Resource
    private NotifyDomainService notifyDomainService;
//    @Resource
//    private SiteConfig siteConfig;
    @Qualifier("huPiJiaoPayConfig")
    @Autowired
    private HuPiJiaoPayConfig config;

    @RequestMapping("/notify")
    public String notify(@RequestParam Map<String, Object> map) {
        log.info("[NotifyController] 开始--支付成功回调：{}", map);
        String hash = HashUtil.hash(map.entrySet(), config.getAppSecret());
        String resHash = map.get("hash").toString();
        if (StringUtils.compare(resHash, hash) != 0) {
            log.error("[NotifyController]支付回调异常：计算hash {}，返回hash：{}", hash, resHash);
        }
        String orderNo = map.get("trade_order_id").toString();
        ConditionCheck.anyNull(new CustomException("参数错误"), orderNo);
        notifyDomainService.handlePaySuccess(Long.valueOf(orderNo));
        log.info("[NotifyController] 结束--支付成功回调：{}", orderNo);

        return "success";
    }
}
