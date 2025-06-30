package com.xujie.business.application.sms.service;

import com.xujie.business.application.sms.dto.SmsDto;

public interface ISmsService {
    public Boolean sendSms(SmsDto.SmsSendRequest smsSendRequest);
}
