package com.xujie.business.application.sms.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;

/**
 * @author Xujie
 * @since 2025/6/30 23:15
 **/


public class SmsUtil {
    private static String generateCode(int length) throws NoSuchAlgorithmException {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = SecureRandom.getInstanceStrong().nextInt(10); // 生成0-9之间的随机数
            code.append(digit);
        }
        return code.toString();
    }

    public static String generateCode() throws NoSuchAlgorithmException {
        return generateCode(4); // 默认生成4位验证码
    }

    /**
     * 渲染模板
     */
    public static String renderTemplate(String template, String... body) {
        MessageFormat messageFormat = new MessageFormat(template);
        return messageFormat.format(body);

    }
}
