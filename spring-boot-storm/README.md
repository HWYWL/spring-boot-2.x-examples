# spring-boot-storm

### 说明
**Storm**是Twitter开源的分布式实时大数据处理框架，随着越来越多的场景对**Hadoop**的**MapReduce**高延迟无法容忍，比如网站统计、推荐系统、预警系统、金融系统(高频交易、股票)等等，大数据实时处理解决方案（流计算）的应用日趋广泛，目前已是分布式技术领域最新爆发点，而**Storm**更是流计算技术中的佼佼者和主流。

storm的数据执行流程如下如

![](https://i.imgur.com/G4r4i2V.png)
我们通过自定义Spout和Bolt的流程来控制数据的传输和处理。


> 此处使用使用kafka作为输入数据源，通过定时任务不断地产生模拟数据，storm获取kafka的数据处理，处理完毕之后吧数据存储在Redis中。



### 依赖
因为storm-core和spring-boot会有slf4j日志的冲突，所以我们需要排除大量的依赖
```xml
<properties>
    <java.version>1.8</java.version>
    <fastjson.version>1.2.59</fastjson.version>
    <log4j-to-slf4j.version>2.0.2</log4j-to-slf4j.version>
    <storm.version>1.1.1</storm.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <exclusions>
            <exclusion>
                <artifactId>log4j-over-slf4j</artifactId>
                <groupId>org.slf4j</groupId>
            </exclusion>
            <exclusion>
                <artifactId>logback-classic</artifactId>
                <groupId>ch.qos.logback</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
            <exclusion>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-to-slf4j</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.apache.storm</groupId>
        <artifactId>storm-core</artifactId>
        <version>${storm.version}</version>
        <scope>provided</scope>
        <!-- 本地运行注释掉，打包集群运行打开-->
        <!--排除相关依赖  -->
        <exclusions>
            <exclusion>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-slf4j-impl</artifactId>
            </exclusion>
            <exclusion>
                <groupId>javax.servlet</groupId>
                <artifactId>servlet-api</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.storm</groupId>
        <artifactId>storm-kafka-client</artifactId>
        <version>${storm.version}</version>
    </dependency>

    <!-- storm-core 排除slf4j后必须添加依赖 -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-to-slf4j</artifactId>
        <version>${log4j-to-slf4j.version}</version>
        <exclusions>
            <exclusion>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-slf4j-impl</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <!-- 引入log4j2依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>${fastjson.version}</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

打包方式也与普通spring boot工程有点不同
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.0</version>
            <configuration>
                <encoding>UTF-8</encoding>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>2.6</version>
            <configuration>
                <archive>
                    <manifest>
                        <addClasspath>true</addClasspath>
                        <classpathPrefix>lib/</classpathPrefix>
                        <mainClass>com.yi.readboard.ReadBoardApplication</mainClass>
                    </manifest>
                </archive>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.1.1</version>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version>2.1.7.RELEASE</version>
                </dependency>
            </dependencies>
            <configuration>
                <keepDependenciesWithProvidedScope>true</keepDependenciesWithProvidedScope>
                <createDependencyReducedPom>true</createDependencyReducedPom>
                <filters>
                    <filter>
                        <artifact>*:*</artifact>
                        <excludes>
                            <exclude>META-INF/*.SF</exclude>
                            <exclude>META-INF/*.DSA</exclude>
                            <exclude>META-INF/*.RSA</exclude>
                        </excludes>
                    </filter>
                </filters>
                <artifactSet>
                    <excludes>
                        <exclude>org.slf4j:slf4j-api</exclude>
                        <exclude>javax.mail:javax.mail-api</exclude>
                        <exclude>org.apache.storm:storm-core</exclude>
                        <exclude>org.apache.storm:storm-kafka</exclude>
                        <exclude>org.apache.logging.log4j:log4j-slf4j-impl</exclude>
                    </excludes>
                </artifactSet>
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer
                                    implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                                <resource>META-INF/spring.handlers</resource>
                            </transformer>
                            <transformer
                                    implementation="org.springframework.boot.maven.PropertiesMergingResourceTransformer">
                                <resource>META-INF/spring.factories</resource>
                            </transformer>
                            <transformer
                                    implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                                <resource>META-INF/spring.schemas</resource>
                            </transformer>
                            <transformer
                                    implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                            <transformer
                                    implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>com.yi.readboard.ReadBoardApplication</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### 配置文件
```
# 指定kafka 代理地址，可以多个
spring.kafka.bootstrap-servers=node01:9092,node02:9092,node03:9092

# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=192.168.80.100
# Redis服务器连接端口
spring.redis.port=6379

# storm 使用配置
# storm名称 为空是本地运行模式,填写是集群运行模式
custom.storm.kafka.name=CustomStorm
custom.storm.kafka.bootstrapServers=node01:9092,node02:9092,node03:9092
custom.storm.kafka.topics[0]=order
```

### 代码说明
只要是两个重点
1、在启动类中因为我们不能使用spring boot内置的Tomcat，所以必须排除掉
```java
@SpringBootApplication
public class SpringBootStormApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootStormApplication.class);
        //不需要web servlet功能，所以设置为WebApplicationType.NONE
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
```

2、在继承BaseBasicBolt中使用自动注入可能会导致注入不成功的问题，所以我们可以使用一个单独的配置类来注入
```java
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    public static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisConfig.stringRedisTemplate = stringRedisTemplate;
    }
}
```

然后在继承BaseBasicBolt的类中重写父类的初始化方法对需要注入的类进行初始化，当然这不是必须的，你也可以直接使用静态调用直接使用
```
/**
 * 统计面板数据
 *
 * @author huangwenyi
 * @date 2019-9-2
 */
@Service
public class CountMoneyBolt extends BaseBasicBolt {
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 初始化方法，程序启动只会调用一次
     *
     * @param stormConf worker的Storm配置
     * @param context   上下文
     */
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        this.stringRedisTemplate = RedisConfig.stringRedisTemplate;
    }

    /**
     * 接收我们上游kafkaspout发送过来的数据，然后将数据保存到redis当中去
     *
     * @param input
     * @param collector
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        // 获取数据
        Object value = input.getValue(4);
        if (null != value && !StringUtils.isEmpty(value.toString())) {
            String jsonStr = value.toString();
            PaymentInfo paymentInfo = JSON.parseObject(jsonStr, PaymentInfo.class);

            // 平台销售总额
            stringRedisTemplate.opsForValue().increment("order:total:price:date", paymentInfo.getPayPrice());
            //平台今天下单的人数
            stringRedisTemplate.opsForValue().increment("order:total:user:date");
            //平台销售的商品数量
            stringRedisTemplate.opsForValue().increment("order:num:user:date");
            //每个商品的总销售额
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":price:date", paymentInfo.getPayPrice());
            //统计每个商品的购买人数
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":user:date");
            // 每个商品的销售数量
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getProductId() + ":num:date");
            //店铺的销售总额
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":price:date", paymentInfo.getPayPrice());
            //店铺的购买人数
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":user:date");
            //每个店铺的销售数量
            stringRedisTemplate.opsForValue().increment("order:" + paymentInfo.getShopId() + ":num:date");
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
```

### 运行
本地模式很简单把
```
custom.storm.kafka.name=CustomStorm
```
不赋值就行
```
custom.storm.kafka.name=
```
然后就可以在idea中启动了

集群模式复杂一点
```
custom.storm.kafka.name=CustomStorm
```
**这个配置一定不能为空**，随便起个名字都可以

然后把jar包上传到我们安装了storm集群的任意一台服务器
然后切换到我们的storm的安装目录执行下面启动命令
```
bin/storm jar /export/servers/read-board-0.0.1-SNAPSHOT.jar com.yi.readboard.ReadBoardApplication
```
命令解释：bin/storm jar jar包全路径 main方法所在的包名+类名

执行成功之后可以在**Storm UI**中查看我们的**Topology**

![](https://i.imgur.com/jIYAycU.png)

好了很简单，尽情的发挥你的创意吧




### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL