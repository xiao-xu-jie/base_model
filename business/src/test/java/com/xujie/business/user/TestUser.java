package com.xujie.business.user;

import com.xujie.business.BusinessApplication;
import com.xujie.business.application.sms.dto.SmsDto;
import com.xujie.business.application.sms.service.impl.SmsService;
import com.xujie.business.mapper.user.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2025/6/30 13:21
 **/

@SpringBootTest(classes = BusinessApplication.class)
public class TestUser {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SmsService smsService;

    @Test
    public void testUser() {
        SmsDto.SmsSendRequest smsSendRequest = new SmsDto.SmsSendRequest();
        smsSendRequest.setPhone("19102849476");
        smsSendRequest.setContent("【小徐科技】AI课-计算机组成原理已经处理完成，如有其他课程需要处理，欢迎下单。");
        smsService.sendSms(smsSendRequest);
    }
}
