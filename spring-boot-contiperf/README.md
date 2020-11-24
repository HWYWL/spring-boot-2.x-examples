# spring-boot-contiperf
ContiPerf是一个轻量级的测试工具,基于JUnit 4 开发,可用于效率测试等。 

### 说明

我们来一个测试用的接口和实现
```java
/**
 * 测试接口
 * @author YI
 * @date 2019-3-7 21:52:09
 */
public interface ContiperfExampleService {

    /**
     * 模拟查找所有数据
     * @return list
     */
    List<String> findAll();
}
```

```java
/**
 * 测试接口实现
 *
 * @author YI
 * @date 2019-3-7 21:54:20
 */
@Slf4j
@Service
public class ContiperfExampleServiceImpl implements ContiperfExampleService {

    private final Random RANDOM = new Random();

    @Override
    public List<String> findAll() {
        try {
            int sleepSecond = RANDOM.nextInt(10);
            log.info("#findAll(): sleep {} seconds..", sleepSecond);
            Thread.sleep(sleepSecond * 1000);
        } catch (InterruptedException e) {
            // ignore
        }

        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            resultList.add("string_" + i);
        }

        return resultList;
    }
}
```

然后编写我们的测试类
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootContiperfApplicationTests {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Autowired
    private ContiperfExampleService contiperfExampleService;

    @Test
    @PerfTest(threads = 1000, duration = 1500)
    public void findAll() {
        contiperfExampleService.findAll().forEach(System.out::println);
    }
}
```

### 执行结果
可以在控制台查看简单的测试类信息
![](https://i.imgur.com/2eeafKK.png)

在**target/contiperf-report/index.html**会生成一个性能报告网页
![](https://i.imgur.com/aMWISlR.png)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL