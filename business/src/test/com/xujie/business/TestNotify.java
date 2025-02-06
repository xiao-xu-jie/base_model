package com.xujie.business;

import com.xujie.business.application.pay.impl.HuPiJiaoPayService;
import com.xujie.business.config.HuPiJiaoPayConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/11/16 13:05
 */
@Slf4j
@EnableConfigurationProperties(HuPiJiaoPayConfig.class)
@SpringBootTest(classes = {HuPiJiaoPayService.class, HuPiJiaoPayConfig.class})
public class TestNotify {
    @Resource
    private HuPiJiaoPayService payService;


    @Test
    public void TestCreateOrder() {
//        Map<String, Object> map = new HashMap<>();
//        map.put(HuPiJiaoPayConstant.ORDER_ID, "12312312");
//        map.put(HuPiJiaoPayConstant.NOTIFY_URL, "http://xxce.cn");
//        map.put(HuPiJiaoPayConstant.RETURN_URL, "http://sanlei.xxce.cn");
//        map.put(HuPiJiaoPayConstant.TITLE, "测试");
//        map.put(HuPiJiaoPayConstant.TOTAL_FEE, 0.1);
//        map.put(HuPiJiaoPayConstant.PLUGINS, "csbz");
//        String order = payService.createOrder(map);
    }
}
