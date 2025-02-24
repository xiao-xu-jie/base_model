package com.xujie.business;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import com.xujie.business.application.es.DTO.OrderStatusEsDTO;
import com.xujie.business.application.es.repository.OrderStatusRepository;
import com.xujie.business.application.pay.entity.OrderRequest;
import com.xujie.business.application.pay.impl.HuPiJiaoPayService;
import com.xujie.business.common.constants.HuPiJiaoPayConstant;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xujie
 * @since 2024/11/16 13:05
 */
@Slf4j
@SpringBootTest
public class TestNotify {
    @Resource
    private HuPiJiaoPayService payService;
    @Resource
    private OrderStatusRepository orderStatusRepository;


    @Test
    public void TestCreateOrder() {
        Map<String, Object> map = new HashMap<>();
        map.put(HuPiJiaoPayConstant.ORDER_ID, "12312312");
        map.put(HuPiJiaoPayConstant.NOTIFY_URL, "http://xxce.cn");
        map.put(HuPiJiaoPayConstant.RETURN_URL, "http://sanlei.xxce.cn");
        map.put(HuPiJiaoPayConstant.TITLE, "测试");
        map.put(HuPiJiaoPayConstant.TOTAL_FEE, 0.1);
        map.put(HuPiJiaoPayConstant.PLUGINS, "csbz");
        OrderRequest request = OrderRequest.builder()
                .orderId(IdUtil.getSnowflakeNextIdStr())
                .notifyUrl("http://xiaoxu.south.takin.cc/notify")
                .title("测试商品")
                .totalFee(0.1)
                .returnUrl("http://xiaoxu.south.takin.cc/")
                .remark("测试")
                .build();
        JSONObject response = payService.createOrder(request);
        log.info(response.toString());
    }

    @Test
    public void TestES() {
        List<OrderStatusEsDTO> byPhone = orderStatusRepository.findByUser("15182659916");
        log.info(byPhone.toString());
    }

}
