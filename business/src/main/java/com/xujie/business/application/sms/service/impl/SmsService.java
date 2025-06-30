package com.xujie.business.application.sms.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xujie.business.application.sms.config.SmsConfiguration;
import com.xujie.business.application.sms.dto.SmsDto;
import com.xujie.business.application.sms.service.ISmsService;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Xujie
 * @since 2025/6/30 23:20
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService implements ISmsService {
    private final SmsConfiguration smsConfiguration;

    @Override
    public Boolean sendSms(SmsDto.SmsSendRequest smsSendRequest) {
        SmsDto.SmsSendHttpRequest smsSendHttpRequest = new SmsDto.SmsSendHttpRequest();
        BeanUtils.copyProperties(smsSendRequest, smsSendHttpRequest);
        if (StringUtil.isBlank(smsSendHttpRequest.getPhone()) || StringUtil.isBlank(smsSendHttpRequest.getContent())) {
            log.error("发送短信失败：手机号或内容不能为空");
            return false;
        }
        return sendSmsRequest(smsSendHttpRequest);
    }

    private Boolean sendSmsRequest(SmsDto.SmsSendHttpRequest smsSendHttpRequest) {
        smsSendHttpRequest.setUrl(smsConfiguration.getUrl());
        smsSendHttpRequest.setChannel(smsConfiguration.getChannel());
        smsSendHttpRequest.setUsername(smsConfiguration.getUsername());
        smsSendHttpRequest.setKey(smsConfiguration.getKey());
        Map<String, Object> bodyMap = buildBodyMap(smsSendHttpRequest);
        log.debug("发送短信请求: {}", JSONUtil.toJsonStr(bodyMap));
        String responseBody = HttpUtil.get(smsSendHttpRequest.getUrl(), bodyMap, 10000);

        if (StringUtil.isBlank(responseBody) || !JSONUtil.isTypeJSON(responseBody)) {
            log.error("发送短信失败：{}", responseBody);
            return false;
        }
        SmsDto.SmsSendResponse smsSendResponse = JSONUtil.toBean(responseBody, SmsDto.SmsSendResponse.class);
        log.debug("发送短信响应: {}", responseBody);
        return smsSendResponse.isSuccess();

    }

    private Map<String, Object> buildBodyMap(SmsDto.SmsSendHttpRequest smsSendHttpRequest) {
        Map<String, Object> bodyMap = MapUtil.newHashMap();
        bodyMap.put("channel", smsSendHttpRequest.getChannel());
        bodyMap.put("username", smsSendHttpRequest.getUsername());
        bodyMap.put("key", smsSendHttpRequest.getKey());
        bodyMap.put("phone", smsSendHttpRequest.getPhone());
        bodyMap.put("content", smsSendHttpRequest.getContent());
        return bodyMap;
    }


}
