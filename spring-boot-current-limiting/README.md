# spring-boot-current-limiting

### 说明
基于令牌桶算法和漏桶算法实现的纳秒级分布式无锁限流插件，完美嵌入SpringBoot、SpringCloud应用，支持接口限流、方法限流、系统限流、IP限流、用户限流等规则，支持设置系统启动保护时间（保护时间内不允许访问），提供快速失败与CAS阻塞两种限流方案。

先引入限流插件
```xml
<dependency>
  <groupId>cn.yueshutong</groupId>
  <artifactId>spring-boot-starter-current-limiting</artifactId>
  <version>0.0.5.RELEASE</version>
</dependency>
```

配置application.properties
```
# 是否开启非注解的限流器
current.limiting.enabled=true
# 是否开启注解限流的作用,FALSE可使注解限流失效
current.limiting.part-enabled=true
# 每秒并发量，支持小数，计算规则：次数/时间(秒级)，为0禁止访问
current.limiting.qps=100
# 是否执行快速失败，FALSE可切换为阻塞
current.limiting.fail-fast=true
# 首次放入令牌（即允许访问）的延迟时间，可作为系统启动保护，单位:毫秒
current.limiting.initial-delay=0
# 是否严格控制请求速率和次数，TRUE即切换为漏桶算法
current.limiting.overflow=true
# 间隔多少秒可以去回收过期的限制器对象
current.limiting.recycling=5
# 执行计划任务的线程池中的核心线程数
current.limiting.corePoolSize=10

```

编写自定义拒绝策略，拒绝策略有两种一种是方法级别的，一种是系统级别的，但**二者不能同时使用**
```java
/**
 * 针对被注解的方法进行自定义拒绝策略
 *
 * @author YI
 * @date 2019-4-27 12:30:53
 */
@Component
public class MyAspectHandler implements CurrentAspectHandler {
    @Override
    public Object around(ProceedingJoinPoint pjp, CurrentLimiter rateLimiter) throws Throwable {
        return MessageResult.errorMsg("当前服务不可用");
    }
}
```

```java
/**
 * 针对系统级别的拒绝策略
 *
 * @author YI
 * @date 2019-4-27 12:30:53
 */
@Component
public class MyInterceptorHandler implements CurrentInterceptorHandler {
    @Override
    public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.getWriter().print("当前系统不可用");
    }
}
```
**注意：以上实现类在Application中只能注入一个。**

定义一个简单的接口
```java
/**
 * 限流测试类
 *
 * @author YI
 * @date 2019-4-27 12:18:18
 */
@RestController
public class CurrentLimitController {

    /**
     * 每秒最大请求数为2
     * @return
     */
    @GetMapping("/hello")
    @CurrentLimiter(QPS = 2)
    public MessageResult hello(){
        return MessageResult.ok("hello world");
    }
}
```
我们使用Postman这个工具来测试一下
![](https://i.imgur.com/EvOMiUm.png)

![](https://i.imgur.com/A8hnihS.png)

![](https://i.imgur.com/kdLMep4.png)

![](https://i.imgur.com/RA9M4ft.png)

从测试可以知道我们的接口限流成功了

### 自定义限流规则
在实际场景中，我们的限流规则并不只是简单的对整个系统或单个接口进行流控，需要考虑的是更复杂的场景。例如：

对请求的目标URL进行限流（例如：某个URL每分钟只允许调用多少次）
对客户端的访问IP进行限流（例如：某个IP每分钟只允许请求多少次）
对某些特定用户或者用户组进行限流（例如：非VIP用户限制每分钟只允许调用100次某个API等）
多维度混合的限流。此时，就需要实现一些限流规则的编排机制。与、或、非等关系。

```java
/**
 * 自定义限流规则，使用此规则系统默认配置的限流规则会失效
 *
 * @author YI
 * @date 2019-4-27 12:35:52
 */
@Component
public class MyRule implements CurrentRuleHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public CurrentProperty rule(HttpServletRequest request) {
        request.getServletPath(); // /hello
        request.getMethod(); // GET
        request.getRemoteHost(); // 127.0.0.1
        request.getSession(); // session

        //返回NULL是无规则的意思，即放行。
        return CurrentPropertyFactory.of("Default", 3, 0, true, true);
    }
}
```
CurrentProperty 构造方法参数说明：

| 参数         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| id           | 标识名。若为IP地址则为IP地址限流，若为用户名则为用户限流，若为访问的URL则为接口限流。 |
| qps          | 每秒并发量。支持小数、分数，计算规则：次数/时间(秒)。为0禁止访问。 |
| initialDelay | 首次放入令牌（即允许访问）延迟时间，可作为系统启动保护时间，单位/毫秒。 |
| failFast     | 是否需开启快速失败。false即切换为阻塞。                      |
| overflow     | 是否严格控制请求速率和次数，true即切换为漏桶算法。               |
| time |该规则限流器对象的存活时间（选填） |
| unit |该规则限流器对象的存活时间单位（选填） |

**注意：一旦自定义规则，即实现CurrentRuleHandler接口，那么系统默认配置的限流规则会失效。注解限流和拦截限流是可以同时作用的。**

具体可参考代码实现

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL