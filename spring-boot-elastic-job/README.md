# spring-boot-elastic-job
elastic-job 是由当当网基于quartz 二次开发之后的分布式调度解决方案

### 说明
elastic-job任务的分布式执行，需要将一个任务拆分为多个独立的任务项，然后由分布式的服务器分别执行某一个或几个分片项。

### 代码
```java
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

```

### 执行结果
![](https://i.imgur.com/v80dGPE.jpg)


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL