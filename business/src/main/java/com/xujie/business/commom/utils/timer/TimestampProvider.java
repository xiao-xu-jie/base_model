package com.xujie.business.commom.utils.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimestampProvider {
    private volatile long cachedTimestamp;
    private final ScheduledExecutorService scheduler;

    public TimestampProvider(long updateIntervalMs) {
        this.cachedTimestamp = System.currentTimeMillis();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        // 定期更新缓存的时间戳
        this.scheduler.scheduleAtFixedRate(
                () -> this.cachedTimestamp = System.currentTimeMillis(),
                updateIntervalMs, updateIntervalMs, TimeUnit.MILLISECONDS
        );
    }

    public long getTimestamp() {
        return cachedTimestamp;
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}