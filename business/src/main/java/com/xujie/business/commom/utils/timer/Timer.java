package com.xujie.business.commom.utils.timer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Timer {
    private static TimestampProvider timestampProvider;

    static {
        log.info("Timer 初始化成功");
        timestampProvider = new TimestampProvider(10);
    }

    public static long currentTimestamp() {
        return timestampProvider.getTimestamp();
    }
}
