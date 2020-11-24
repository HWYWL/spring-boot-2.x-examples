# spring-boot-dubbo-zookeeper-hystrix-annotation

### 说明
使用注解的方式使用hystrix

我需要引入几个必要的jar
```
<dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-core</artifactId>
            <version>1.5.12</version>
        </dependency>

        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-metrics-event-stream</artifactId>
            <version>1.5.12</version>
        </dependency>

        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-javanica</artifactId>
            <version>1.5.12</version>
        </dependency>

        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
        </dependency>
```

然后是拦截中心
```
/**
 * 拦截中心
 * @author YI
 * @date 2018-9-8 16:37:01
 */
@Configuration
public class HystrixConfig {

    /**
     * 用来拦截处理HystrixCommand注解
     * @return
     */
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    /**
     * 用来像监控中心Dashboard发送stream信息
     * @return
     */
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }
}
```

如此简单我们就可以使用注解的方式提供熔断降级服务
```
/**
 * 消费者
 * @author YI
 * @date 2018-8-20 17:05:30
 */
@RestController
public class HelloController {

    @Reference(
            version = "${hello.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private HelloService helloService;

    /**
     * 出错时回调sayHelloFallback 方法
     * @param name
     * @return
     */
    @GetMapping("hello/{name}")
    @HystrixCommand(fallbackMethod = "sayHelloFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public String sayHello(@PathVariable String name){
        return helloService.sayHello(name);
    }

    /**
     * 出错时回调sayGoodbyeFallback 方法
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "sayGoodbyeFallback")
    @GetMapping("goodbye/{name}")
    public String sayGoodbye(@PathVariable String name){
        return helloService.sayGoodbye(name);
    }

    public String sayHelloFallback(String name){
        System.out.println("sayHello回调函数");

        return "OK";
    }

    public String sayGoodbyeFallback(String name){
        System.out.println("sayGoodbye回调函数");

        return "OK";
    }
}
```

具体实现请看源码

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL