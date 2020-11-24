package com.yi.elastic.job.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.yi.elastic.job.task.SimpleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置zookeeper注册中心
 * @author YI
 * @date 2019-1-4 10:27:05
 */
@Configuration
public class ElasticJobConfig {
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    /**
     * 配置任务监听器
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new MyElasticJobListener();
    }

    /**
     * 任务初始化
     * @param simpleTask                任务类
     * @param cron                      cron时间表达式
     * @param shardingTotalCount        分片总数
     * @param shardingItemParameters    分片项参数
     * @return
     */
    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleTask simpleTask,
                                           @Value("${stockJob.cron}") final String cron,
                                           @Value("${stockJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${stockJob.shardingItemParameters}") final String shardingItemParameters) {

        MyElasticJobListener elasticJobListener = new MyElasticJobListener();

        return new SpringJobScheduler(simpleTask, regCenter,
                getLiteJobConfiguration(simpleTask.getClass(), cron, shardingTotalCount, shardingItemParameters),
                elasticJobListener);
    }

    /**
     * 配置任务详细信息
     * @param jobClass                  任务类
     * @param cron                      cron时间表达式
     * @param shardingTotalCount        分片总数
     * @param shardingItemParameters    分片项参数
     * @return
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {

        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
                JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                        .shardingItemParameters(shardingItemParameters).build()
                , jobClass.getCanonicalName())
        ).overwrite(true).build();
    }
}
