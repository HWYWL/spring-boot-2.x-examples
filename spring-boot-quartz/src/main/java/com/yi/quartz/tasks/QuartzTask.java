package com.yi.quartz.tasks;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务业务处理类
 * 该类需要继承QuartzJobBean类并重写executeInternal方法来实现定时任务的具体逻辑
 * 注解 @DisallowConcurrentExecution 用于标记定时任务禁止并发执行
 * @author YI
 * @date 2018-11-27 09:56:07
 */
@DisallowConcurrentExecution
public class QuartzTask extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(QuartzTask.class);

    /**
     * 该方法用于实现定时任务的具体逻辑
     */
    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("------------- 定时任务开始 -------------");
        log.info("------------- 我是世界上最帅的定时任务 -------------");
        log.info("------------- 定时任务结束 -------------\r\n");
    }

}
