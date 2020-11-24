# spring-boot-xml

### 说明
我们使用spring boot一般是使用配置文件进行配置，但也会遇到有写公司使用xml配置的，接下来我们使用xnk配置启动一个应用(不推荐使用)。

我们定义一个接口和一个执行程序：
```java
/**
 * xml暴露接口
 * @author YI
 * @date 2019-1-28 10:19:50
 */
@Service
public class HelloWorldService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }
}
```

```java
/**
 * 启动执行
 *
 * @author YI
 * @date 2019-1-28 10:26:07
 */
@Component
public class HelloWorldRunner implements CommandLineRunner {
    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... args) {
        System.out.println(this.helloWorldService.getHelloMessage());
    }
}
```

随后我们把这两个接口暴露在xml中，是系统能识别
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
				http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    <context:property-placeholder/>

    <bean id="helloService" class="com.yi.xml.service.HelloWorldService"/>
    <bean id="helloRunner" class="com.yi.xml.service.HelloWorldRunner"/>

</beans>
```

还需要在启动文件程序时指定我们的xml文件位置：
```java
/**
 * xml配置
 *
 * @author YI
 * @date 2019-1-28 10:21:24
 */
@SpringBootApplication
public class SpringBootXmlApplication {

    private static final String CONTEXT_XML = "classpath:/META-INF/application-context.xml";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setSources(Collections.singleton(CONTEXT_XML));
        application.run(args);
    }

}
```

执行结果：
![](https://i.imgur.com/aIgK235.png)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL