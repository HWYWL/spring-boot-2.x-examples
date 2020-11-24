package com.yi.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 * @author YI
 * @date 2018-11-5 10:30:08
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 5秒钟执行一次
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("现在时间是 {}", dateFormat.format(new Date()));
    }

    /**
     * 2秒钟执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void cronTask() {
        log.info("cron 现在时间是 {}", dateFormat.format(new Date()));
    }
}
