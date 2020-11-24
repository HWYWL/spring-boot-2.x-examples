# spring-boot-consul

### 说明
Consul 是一种服务网络解决方案，用于跨任何运行时平台和公共或私有云连接和保护服务，
它提供了以下几个常用的功能：
- 服务发现
- 运行状况检查
- KV 存储
- 安全服务通信
- 多数据中心

![8fWhh6.png](https://s1.ax1x.com/2020/03/21/8fWhh6.png)


它的下载和安装都非常方便：https://www.consul.io
下载完，解压，然后打开cmd进入加压的目录，可以使用以下命令启动（dev的配置只存在内存，重启后消失）
```
consul agent -dev
```
因为是测试，所以我们起一个单节点就够了

浏览器访问它的UI界面：
```
http://localhost:8500/ui
```

![8fhkse.png](https://s1.ax1x.com/2020/03/21/8fhkse.png)

我们点到**Key/Value**,点击添加我们的配置

![8f4SmQ.png](https://s1.ax1x.com/2020/03/21/8f4SmQ.png)

**key**例如：config/api/respon表示，config目录下有一个api目录，api目录下有一个名为respon的key。
**Value**支持JSON、HCL、YAML三种格式。
我们可以多建几个key/value等会用于测试
![8f5CuD.png](https://s1.ax1x.com/2020/03/21/8f5CuD.png)

因为使用官方的SD需要引入**Spring Cloud**,很大一个包，所以这里我们使用它开放的api自己封装一个，获取值的工具类。
application.properties 引入两个配置
```
# 获取单个配置
consul.val=http://localhost:8500/v1/kv/{key}?raw=true
# 获取所有配置
consul.valAll=http://localhost:8500/v1/kv/{key}/?recurse
```

**ConsulConfig**
```
/**
 * @author YI
 * @date 2020/3/21 11:10
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "consul")
public class ConsulConfig {
    /**
     * 获取具体路径下的数据
     */
    private String val;
    /**
     * 目录下的所有数据
     */
    private String valAll;
}
```

**ConsulUtil**
```
/**
 * consul配置
 * @author YI
 * @date 2020/3/21 16:33
 */
@Component
public class ConsulUtil {
    @Autowired
    ConsulConfig consulConfig;

    /**
     * 获取单个
     * @param key 配置的路径，到具体的key
     * @return
     */
    public String getValue(String key){
        String template = consulConfig.getVal();
        String result = StrUtil.format(template, Dict.create().set("key", key));
        return HttpUtil.get(result);
    }

    /**
     * 获取目录下的配置列表
     * @param key 配置的路径，不到具体的key
     * @return
     */
    public List<Consul> getValAll(String key){
        String template = consulConfig.getValAll();
        String result = StrUtil.format(template, Dict.create().set("key", key));
        String resopn = HttpUtil.get(result);
        JSONArray array = JSONUtil.parseArray(resopn);
        List<Consul> consuls = JSONUtil.toList(array, Consul.class);
        consuls.forEach(e -> e.setValue(StrUtil.removeAllLineBreaks(Base64.decodeStr(e.getValue()))));

        return consuls;
    }
}
```

搞定，写个测试类试一试：
```
@SpringBootTest
class SpringBootConsulApplicationTests {
    @Autowired
    ConsulUtil consulUtil;

    @Test
    void test01() {
        String value = consulUtil.getValue("config/api/respon");
        System.out.println(value);
    }

    @Test
    void test02() {
        List<Consul> valAll = consulUtil.getValAll("config/api/");
        valAll.forEach(e -> System.out.println(e.getValue()));
    }

}
```
![8foqHg.png](https://s1.ax1x.com/2020/03/21/8foqHg.png)

![8fobDS.png](https://s1.ax1x.com/2020/03/21/8fobDS.png)

ok,没问题，就这样啦啦啦。
### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://hwy.ac.cn
- GitHub：https://github.com/HWYWL