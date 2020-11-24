# spring-boot-dubbo-zookeeper

### 说明
通过整合dubbo-spring-boot 和 zookeeper 到Spring Boot中实现服务治理

项目为聚合项目，通过一下几个子项目模块提供服务：
	
	基础公共接口：spring-boot-dubbo-zookeeper-base-interface
    服务提供者：spring-boot-dubbo-zookeeper-provider
	服务消费者：spring-boot-dubbo-zookeeper-consumer

在application.yml中配置zookeeper的地址可以单击也可以集群。

项目启动我们可以通过打印的日志或者Dubbo Admin查看，日志打印即可查看，我展示一下Dubbo Admin的显示：
![](https://i.imgur.com/WCaVKxU.jpg)

服务治理：提供者
![](https://i.imgur.com/jFB8P56.jpg)

服务治理：消费者
![](https://i.imgur.com/ZFXgkNK.jpg)

项目启动之后请求：
```
http://localhost:9091/hello/xiaohua
http://localhost:9091/hello/goodbye/meinv
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL