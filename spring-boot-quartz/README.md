# spring-boot-aop-log

### 说明
我们指定好业务执行类，然后通过Cron设置好执行时间间隔
![](https://i.imgur.com/kfOdG12.jpg)

我们来看看执行结果
![](https://i.imgur.com/q2QqYEq.jpg)

业务比较简单，看代码吧
```java
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
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL