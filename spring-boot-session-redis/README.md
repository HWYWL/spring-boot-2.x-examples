# spring-boot-session-redis

### 说明
使用spring自带的session实现分布式应用的session共享。

其实使用非常简单我们在pom中引入的我们依赖,主要是**spring-boot-starter-data-redis**和**spring-boot-starter-data-redis**这两个
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!--分布式应用共享session-->
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-data-redis</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.8</version>
    </dependency>
</dependencies>
```

在application.properties按照常规引入redis配置，就可以，是不是非常简单
```
spring.redis.host=127.0.0.1
spring.redis.database=1
spring.redis.port=6379

server.port=8080
```

然后编写一个小小的demo测试一下
```java
/**
 * session 操作
 *
 * @author YI
 * @date 2019-6-5 10:02:06
 */
@RestController
public class SessionController {
    @Value("${server.port}")
    Integer port;

    /**
     * 设置session
     * @param session session
     * @return
     */
    @GetMapping("/set")
    public MessageResult set(HttpSession session) {
        User user = User.builder().id(1).username("YI").role((byte)0).del((byte)0).remark("超级管理员账户").crttime(new Date()).build();
        session.setAttribute("user", user);

        return MessageResult.ok(port);
    }

    /**
     * 获取session
     * @param session session
     * @return
     */
    @GetMapping("/get")
    public MessageResult get(HttpSession session) {
        return MessageResult.ok(session.getAttribute("user") + ":" + port);
    }
}
```

代码中返回端口号是为了让我们区分那台服务器处理的数据，然后我们使用8080和8081分别打两个jar包出来测试。
![](https://i.imgur.com/yF09GfC.png)

然后配置我们的nginx，在http模块下加入负载配置
```
upstream yi.com{
	server 127.0.0.1:8080 weight=1;
	server 127.0.0.1:8081 weight=2;
}
server {
    listen       80;
    server_name  localhost;

    #charset koi8-r;

    #access_log  logs/host.access.log  main;

    location / {
    	proxy_pass http://yi.com;
    	proxy_redirect default;
    }

    # location / {
    #     root   html;
    #     index  index.html index.htm;
    # }

    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   html;
    }
}
```

然后在host文件中改一下域名
![](https://i.imgur.com/o5PVVLN.png)

至此所有配置已完成，我们来访问试试看，先访问set接口，生成session数据
![](https://i.imgur.com/grDHFob.png)
我们可以看到这里走的是8080那台服务器，我们再访问一下get接口，看8081服务器能不能拿到session
![](https://i.imgur.com/aO8NUac.png)

数据成功获取，session同步完美，我们去redis看一下生成的数据
![](https://i.imgur.com/SNZVHQ1.png)

好了演示到此结束，具体实现请看代码，也很简单！


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL