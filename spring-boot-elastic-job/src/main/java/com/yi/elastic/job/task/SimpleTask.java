package com.yi.elastic.job.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * 任务
 * @author YI
 * @date 2019-1-4 10:31:18
 */
@Component
public class SimpleTask implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {

        System.out.println(String.format("------Thread ID: %s, 任務總片數: %s, " +
                        "当前分片項: %s.当前參數: %s," +
                        "当前任務名稱: %s.当前任務參數: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));
    }
}
