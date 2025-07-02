package com.xujie.business.controller.common;

import com.xujie.business.application.sms.dto.SmsDto;
import com.xujie.business.application.sms.service.impl.SmsService;
import com.xujie.business.application.sms.utils.SmsUtil;
import com.xujie.business.dto.common.CommonDto;
import com.xujie.future.contract.response.ResponseEntity;
import com.xujie.future.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xujie.business.commom.constants.SmsConstant.PHONE_CODE_TEMPLATE;
import static com.xujie.business.commom.constants.SmsConstant.SMS_CODE_KEY;

/**
 * 通用请求处理控制器
 *
 * @author Xujie
 * @since 2025/7/1 13:18
 **/
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {
    private final SmsService smsService;
    private final RedisService redisService;

    /**
     * 发送短信验证码
     *
     * @param request
     * @return
     */
    @SneakyThrows
    @PostMapping("/sms/send")
    public ResponseEntity<String> sendSms(@RequestBody CommonDto.SmsSendRequest request) {
        SmsDto.SmsSendRequest smsSendRequest = new SmsDto.SmsSendRequest();
        String smsCode = SmsUtil.generateCode();
        // 建议是否已经存在验证码
        String existingCode = redisService.get(SMS_CODE_KEY + request.getPhone());
        if (existingCode != null) {
            return ResponseEntity.fail("请勿重复发送验证码");
        }
        // 存储验证码到 Redis
        boolean flag = redisService.set(SMS_CODE_KEY + request.getPhone(), smsCode, 60L);
        if (!flag) {
            return ResponseEntity.fail("验证码发送失败");
        }
        BeanUtils.copyProperties(request, smsSendRequest);
        String template = SmsUtil.renderTemplate(PHONE_CODE_TEMPLATE, smsCode);
        smsSendRequest.setContent(template);
        Boolean res = smsService.sendSms(smsSendRequest);
        if (!res) {
            // 如果发送失败，删除 Redis 中的验证码
            redisService.remove(SMS_CODE_KEY + request.getPhone());
            return ResponseEntity.fail("验证码发送失败");
        }
        return ResponseEntity.success();
    }
}
