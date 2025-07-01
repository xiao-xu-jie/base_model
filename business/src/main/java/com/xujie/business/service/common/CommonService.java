package com.xujie.business.service.common;

import com.xujie.future.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.xujie.business.commom.constants.SmsConstant.SMS_CODE_KEY;

/**
 * @author Xujie
 * @since 2025/7/1 13:33
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {
    private final RedisService redisService;

    /**
     * 建议验证码
     */
    public Boolean verifyCode(String phone, String code) {
        String redisKey = SMS_CODE_KEY + phone;
        String redisCode = redisService.get(redisKey);
        if (redisCode == null) {
            log.warn("验证码已过期或不存在，手机号: {}", phone);
            return false;
        }
        if (!redisCode.equals(code)) {
            log.warn("验证码不匹配，手机号: {}, 输入的验证码: {}, 正确的验证码: {}", phone, code, redisCode);
            return false;
        }
        // 验证成功后删除验证码
        redisService.remove(redisKey);
        log.info("验证码验证成功，手机号: {}", phone);
        return true;
    }
}
