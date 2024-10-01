package com.xujie.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author Xujie
 * @since 2024/10/1 20:26
 */
@Slf4j
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    log.info("初始化定时任务线程池");
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setThreadNamePrefix("business-task-");
    taskScheduler.initialize();
    taskRegistrar.setTaskScheduler(taskScheduler);
    log.info("初始化定时任务线程池完成");
  }
}
