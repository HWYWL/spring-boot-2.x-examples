package com.yi.quartz.config;

import com.yi.quartz.tasks.QuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz配置
 * @author YI
 * @date 2018-11-27 09:52:41
 */
@Configuration
public class QuartzConfig {

    /**
     * JobDetail 用于指定定时任务具体的类
     */
    @Bean
    public JobDetail quartzTaskServiceJobDetail() {
        return JobBuilder.newJob(QuartzTask.class).withIdentity("quartzTask").storeDurably().build();
    }

    /**
     * Trigger 用于指定定时任务触发的机制
     */
    @Bean
    public Trigger quartzTaskServiceTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");

        return TriggerBuilder.newTrigger().forJob(quartzTaskServiceJobDetail())
                .withIdentity("quartzTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
