# spring-boot-presto

### 说明
presto是基于内存的并行计算，Facebook推出的分布式SQL交互式查询引擎，多个节点管道式执行支持任意数据源，数据规模GB~PB，是一种Massively parallel processing（mpp）(大规模并行处理)模型，数据规模PB，不是把PB数据放到内存，只是在计算中拿出一部分放在内存、计算、抛出、再拿。

presto支持多数据源、支持SQL、扩展性（可以自己扩展新的connector）、混合计算（同一种数据源的不同库 or表；将多个数据源的数据进行合并）、高性能、流水线（pipeline），我在网上找了一张图

![](https://i.imgur.com/mOLBfVj.png)

我们这里使用连接Hive操作演示

presto连接配置(**使用用户名/密码进行身份验证需要启用SSL**)：
```
# presto配置
presto.datasource.jdbc-url=jdbc:presto://52.83.183.51:8889/hive/user_center
presto.datasource.driver-class-name=com.facebook.presto.jdbc.PrestoDriver
presto.datasource.username=root
presto.datasource.password=
```

配置数据源：
```java
/**
 * presto 连接配置
 *
 * @author YI
 * @date 2019-10-9
 */
@Configuration
@Data
public class HiveDruidConfig {
    @Bean(name = "prestoDatasource")
    @ConfigurationProperties(prefix = "presto.datasource")
    public DataSource prestoDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "prestoJdbcTemplate")
    public JdbcTemplate prestoJdbcTemplate(DataSource prestoDatasource) {
        return new JdbcTemplate(prestoDatasource);
    }
}
```

其后使用和我们平时写SQL并没用多大的区别,我们来个测试类测试一下：

```java
@SpringBootTest
class SpringBootPrestoApplicationTests {
    @Autowired
    JdbcTemplate prestoJdbcTemplate;

    @Test
    void select() {
        String sql = "select app_id as appId,open_id as openId,event_id as eventId from user_center.minigame_log_data where dt = date '2019-11-25' and app_id = 61 and event_id = 155 limit 100";

        List<Map<String, Object>> maps = prestoJdbcTemplate.queryForList(sql);

        List<UserInfo> userInfos = maps.stream().map(e -> BeanUtil.mapToBean(e, UserInfo.class, true)).collect(Collectors.toList());

        System.out.println(userInfos);
    }
}
```

使用很简单这里就不再累述了。


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL