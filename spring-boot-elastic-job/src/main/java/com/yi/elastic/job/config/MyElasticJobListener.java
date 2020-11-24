package com.yi.elastic.job.config;

import cn.hutool.core.date.DateUtil;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务监听器
 * @author YI
 * @date 2019-1-4 10:29:26
 */
public class MyElasticJobListener implements ElasticJobListener {
    private static final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);

    private long beginTime = 0;

    /**
     * 任务开始之前
     * @param shardingContexts  分片上下文
     */
    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        logger.info("===>{} 任务开始时间: {},耗时(ms): {} <===",shardingContexts.getJobName(), DateUtil.date(endTime).toString(), endTime - beginTime);
    }

    /**
     * 任务结束之后
     * @param shardingContexts 分片上下文
     */
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();

        logger.info("===>{} 任务结束时间: {} <===",shardingContexts.getJobName(), DateUtil.date(beginTime).toString());
    }
}
