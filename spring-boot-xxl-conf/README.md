# spring-boot-xxl-conf

### 说明
XXL-CONF 是一个轻量级分布式配置管理平台，拥有"轻量级、秒级动态推送、多环境、跨语言、跨机房、配置监听、权限控制、版本回滚"等特性。现已开放源代码，开箱即用。

## 部署
把源码**clone**下来，打开xxl-conf-admin运行即可，非常简单.
访问：http://localhost:8099/xxl-conf-admin

![1.png](http://yanxuan.nosdn.127.net/9b2463edcfd2b7b88426706038daae33.png)

## 配置中心Java接入（Spring Boot）
引入配置
```
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-conf-core</artifactId>
    <version>1.6.1</version>
</dependency>
```

**application.properties**配置
```
# xxl-conf
# 配置中心跟地址，必填；
xxl.conf.admin.address=http://localhost:8099/xxl-conf-admin
# 环境配置，必填；如"test、ppe、product"等，指定配置加载环境；
xxl.conf.env=test
# 配置中心接入验证TOKEN，选填，非空时启用，进行安全严重
xxl.conf.access.token=H6l8sqOxJlqdHleasRAIYzIdQnwK9jsFifok
# 配置快照文件地址，必填；会周期性缓存到本地快照文件中，当从配置中心获取配置失败时，将会使用使用本地快照文件中的配置数据；提高系统可用性；
xxl.conf.mirrorfile=/data/applogs/xxl-conf/xxl-conf-mirror-sample.properties
```

**配置工厂**
```java
@Slf4j
@Configuration
public class Config {
    @Value("${xxl.conf.admin.address}")
    private String adminAddress;

    @Value("${xxl.conf.env}")
    private String env;

    @Value("${xxl.conf.access.token}")
    private String accessToken;

    @Value("${xxl.conf.mirrorfile}")
    private String mirrorfile;


    @Bean
    public XxlConfFactory xxlConfFactory() {

        XxlConfFactory xxlConf = new XxlConfFactory();
        xxlConf.setAdminAddress(adminAddress);
        xxlConf.setEnv(env);
        xxlConf.setAccessToken(accessToken);
        xxlConf.setMirrorfile(mirrorfile);

        log.info(">>>>>>>>>>> xxl-conf config init.");
        return xxlConf;
    }
}
```

测试使用
```
@Slf4j
@Controller
public class IndexController {
    @XxlConf("default.key02")
    public String paramByAnno;

    /**
     * 配置变更监听示例：可开发Listener逻辑，监听配置变更事件；可据此实现动态刷新JDBC连接池等高级功能；
     */
    static {
        XxlConfClient.addListener("default.key01", (key, value) -> log.info("配置变更事件通知：{}={}", key, value));
    }

    @RequestMapping("/index")
    @ResponseBody
    public List<String> index(){

        List<String> list = new LinkedList<>();

        //方式1: API方式
        String paramByApi = XxlConfClient.get("default.key01", null);
        list.add("1、API方式: default.key01=" + paramByApi);

        //@XxlConf 注解方式
        list.add("2、@XxlConf 注解方式: default.key02=" + paramByAnno);

        return list;
    }

}
```

## 配置中心 http 服务（多语言支持）

- 配置批量获取接口

**请求URL：** 
- ` http://localhost:8099/xxl-conf-admin/conf/find `
  
**请求方式：**
- POST 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|accessToken |是  |string |请求令牌   |
|env |是  |string | 环境标识    |
|keys     |是  |string | 配置Key列表    |

 **请求示例**
请求数据格式如下，放置在 RequestBody 中，JSON格式：
```
{
    "accessToken" : "xx",
    "env" : "xx",
    "keys" : [
        "key01",
        "key02"
    ]
}
```
 **返回示例**

``` 
{
  "code": 200,
  "msg": null,     
  "data": {         
    "key01": "22",
    "key02": "111"
  }
}
```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |200 表示正常、其他失败  |
|msg |string   |错误提示消息  |
|data |array   |配置信息，KV格式  |


----------

- 配置实时监控接口

**请求URL：** 
- ` http://localhost:8099/xxl-conf-admin/conf/monitor `
  
**请求方式：**
- POST 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|accessToken |是  |string |请求令牌   |
|env |是  |string | 环境标识    |
|keys     |是  |string | 配置Key列表    |

 **请求示例**
请求数据格式如下，放置在 RequestBody 中，JSON格式：
```
{
    "accessToken" : "xx",
    "env" : "xx",
    "keys" : [
        "key01",
        "key02"
    ]
}
```
 **返回示例**

``` 
{
  "code": 200, 
  "msg": "Monitor key update."
}
```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |200 表示正常，一直阻塞到配置变更或超时；非200 表示请求异常  |
|msg |string   |错误提示消息  |

 **备注** 
long-polling 接口，主动阻塞一段时间（默认30s）；直至阻塞超时或配置信息变动时响应；

## http demo （java）
```
    @Test
    void find() {
        String url = "http://localhost:8099/xxl-conf-admin/conf/find";

        List<String> keys = new ArrayList<>();
        ConfBean bean = new ConfBean();

        bean.setAccessToken("H6l8sqOxJlqdHleasRAIYzIdQnwK9jsFifok");
        bean.setEnv("test");

        keys.add("default.key01");
        keys.add("default.key02");
        keys.add("default.key03");
        bean.setKeys(keys);

        String body = HttpUtil.createGet(url).body(JSONUtil.toJsonPrettyStr(bean)).execute().body();
        System.out.println(body);
    }

    @Test
    void monitor() {
        String url = "http://localhost:8099/xxl-conf-admin/conf/monitor";

        List<String> keys = new ArrayList<>();
        ConfBean bean = new ConfBean();

        bean.setAccessToken("H6l8sqOxJlqdHleasRAIYzIdQnwK9jsFifok");
        bean.setEnv("test");

        keys.add("default.key01");
        keys.add("default.key02");
        keys.add("default.key03");
        bean.setKeys(keys);

        String body = HttpUtil.createGet(url).body(JSONUtil.toJsonPrettyStr(bean)).execute().body();
        System.out.println(body);
    }
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://hwy.ac.cn
- GitHub：https://github.com/HWYWL