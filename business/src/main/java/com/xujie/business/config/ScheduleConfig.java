package com.xujie.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * ScheduleConfig
 *
 * @author Xujie
 * @since 2024/10/10 11:41
 */
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(getSchedule());
  }

  @Bean("scheduleExecutor")
  public TaskExecutor getSchedule() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setPoolSize(20);
    taskScheduler.setThreadNamePrefix("schedule-");
    taskScheduler.initialize();
    return taskScheduler;
  }
}
