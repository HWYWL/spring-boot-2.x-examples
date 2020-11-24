# spring-boot-nacos-config

### 说明
Nacos 提供了一组简单易用的特性集，可以快速实现动态服务发现、服务配置、服务元数据及流量管理。
今天我们就使用Nacos的服务配置功能搭建我们的配置中心。

![8WO6hQ.jpg](https://s1.ax1x.com/2020/03/21/8WO6hQ.jpg)


安装为了方便这里使用docker安装Nacos，避免各种windows的环境问题。
```
git clone https://github.com/nacos-group/nacos-docker.git
cd nacos-docker
```
因为是测试，所以我们起一个单节点就够了,单机模式 Derby
```
docker-compose -f example/standalone-derby.yaml up
```

浏览器访问它的UI界面：
```
http://127.0.0.1:8848/nacos/
```

![8WjGzd.png](https://s1.ax1x.com/2020/03/21/8WjGzd.png)

点击最右边的加号，可以添加我们的配置，下面是他常用的配置格式：
- TEXT
- JSON
- XML
- YAML
- HTML
- Properties

我们配置一个JSON和一个Properties来测试一下

![8WvFmt.png](https://s1.ax1x.com/2020/03/21/8WvFmt.png)

![8WvPOI.png](https://s1.ax1x.com/2020/03/21/8WvPOI.png)

接下来就是代码编写，直接建一个Spring boot工程，然后加入Nacos依赖
```
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
    <version>0.2.7</version>
</dependency>
```

因为使用官方SDK的时候我一直拿到的数据都是null，所以我这里打算直接使用他的open-api直接访问取数据。

**application.properties**
```
# nacos 配置服务地址
nacos.config.server-addr=127.0.0.1:8848

# 获取配置/发布配置/删除配置
conf.configs=http://127.0.0.1:8848/nacos/v1/cs/configs
# 监听配置
conf.listener=http://127.0.0.1:8848/nacos/v1/cs/configs/listener
```

**NacosConfig**
```
@Data
@Configuration
@ConfigurationProperties(prefix = "conf")
public class NacosConfig {
    /**
     * 获取配置/发布配置/删除配置 URL
     */
    private String configs;
    /**
     * 监听配置 url
     */
    private String listener;
}
```

然后我们就可以来测试了
```
@SpringBootTest
class SpringBootNacosConfigApplicationTests {
    @Autowired
    NaCosUtil naCosUtil;

    @Test
    void test01() {
        String properties = naCosUtil.getNacosConfig("ll.properties", "DEFAULT_GROUP");
        String json = naCosUtil.getNacosConfig("OLConsumer", "HWY_GROUP");

        System.out.println(properties);
        System.out.println("************************************************************************");
        System.out.println(json);
    }
}
```

![8WzzfU.png](https://s1.ax1x.com/2020/03/21/8WzzfU.png)

很简单，下面是官方的文档，其余 **监听配置**、**发布配置**、**删除配置** 没写的接口也在下面：
```
https://nacos.io/zh-cn/
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://hwy.ac.cn
- GitHub：https://github.com/HWYWL