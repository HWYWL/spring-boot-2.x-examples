# spring-boot-async

### 说明
通过在方法上设置**@Async**注解，可使得方法被异步调用。也就是说调用者会在调用时立即返回，而被调用方法的实际执行是交给Spring的TaskExecutor来完成。

我们在配置中让它开启
```java
/**
 * 配置扫描接口路径,开启异步接口
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
@EnableAsync
@MapperScan(basePackages = {"com.yi.async.mapper"})
public class Config {

}

```

然后在我们需要的方法上加上**@Async**注解
```java
/**
 * 百科数据操作
 *
 * @author YI
 * @date 2019-3-19 22:18:44
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BaikeServiceImpl implements BaikeService {
    @Resource
    BaikeDAO baikeDAO;

    /**
     * 异步接口
     * @param baike 百科信息
     * @return 只能返回void或者Future<T>类型
     */

    @Async
    @Override
    public Future<String> save(Baike baike) {
        int i = baikeDAO.insertSelective(baike);

        return new AsyncResult<>("插入行数:" + i);
    }
}
```

需要注意的是因为代理的关系，在同一类中不能调用异步方法使不会生效的
![](https://i.imgur.com/724KzLb.png)

通过调用接口我们发现，启动了两条线程来执行我们的操作。

比较简单，具体看代码吧

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL