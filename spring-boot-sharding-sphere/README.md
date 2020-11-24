# spring-boot-sharding-sphere

### 说明
**ShardingSphere**是一套开源的分布式数据库中间件解决方案组成的生态圈，它由**Sharding-JDBC、Sharding-Proxy**和**Sharding-Sidecar**（计划中）这3款相互独立的产品组成。 他们均提供标准化的数据分片、分布式事务和数据库治理功能，可适用于如Java同构、异构语言、容器、云原生等各种多样化的应用场景。

好了废话不多说我们进入正题，为了操作数据方便我们使用MyBatis-Plus进行操作

先进入我们的pom.xml进行依赖，下面是我们主要的依赖
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!--Mybatis-Plus-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.1.1</version>
    </dependency>
    
    <!--shardingsphere-->
    <dependency>
        <groupId>io.shardingsphere</groupId>
        <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
        <version>3.1.0</version>
    </dependency>
    <dependency>
        <groupId>io.shardingsphere</groupId>
        <artifactId>sharding-jdbc-spring-namespace</artifactId>
        <version>3.1.0</version>
    </dependency>

    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.8</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

然后是数据库ds0和ds1的SQL
```sql
CREATE DATABASE IF NOT EXISTS `ds0` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `ds0`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
```

```sql
CREATE DATABASE IF NOT EXISTS `ds1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `ds1`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_0
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
```

然后配置我们application.properties文件(**❀重点**)
```
# 数据源 ds0,ds1
sharding.jdbc.datasource.names=ds0,ds1
# 第一个数据库
sharding.jdbc.datasource.ds0.type=com.zaxxer.hikari.HikariDataSource
sharding.jdbc.datasource.ds0.driver-class-name=com.mysql.cj.jdbc.Driver
sharding.jdbc.datasource.ds0.jdbc-url=jdbc:mysql://localhost:3306/ds0?characterEncoding=utf-8&serverTimezone=UTC
sharding.jdbc.datasource.ds0.username=root
sharding.jdbc.datasource.ds0.password=root

# 第二个数据库
sharding.jdbc.datasource.ds1.type=com.zaxxer.hikari.HikariDataSource
sharding.jdbc.datasource.ds1.driver-class-name=com.mysql.cj.jdbc.Driver
sharding.jdbc.datasource.ds1.jdbc-url=jdbc:mysql://localhost:3306/ds1?characterEncoding=utf-8&serverTimezone=UTC
sharding.jdbc.datasource.ds1.username=root
sharding.jdbc.datasource.ds1.password=root

# 水平拆分的数据库（表） 配置分库 + 分表策略 行表达式分片策略
# 分库策略
sharding.jdbc.config.sharding.default-database-strategy.inline.sharding-column=id
# 此语句可以理解为user表根据id模2，而分成2张表，表名称为user_0到user_1
sharding.jdbc.config.sharding.default-database-strategy.inline.algorithm-expression=ds$->{id % 2}

# 分表策略 其中user为逻辑表 分表主要取决于age行
sharding.jdbc.config.sharding.tables.user.actual-data-nodes=ds$->{0..1}.user_$->{0..1}
sharding.jdbc.config.sharding.tables.user.table-strategy.inline.sharding-column=age
# 分片算法表达式
sharding.jdbc.config.sharding.tables.user.table-strategy.inline.algorithm-expression=user_$->{age % 2}

# 主键 UUID 18位数 如果是分布式还要进行一个设置 防止主键重复
#sharding.jdbc.config.sharding.tables.user.key-generator-column-name=id

# 打印执行的数据库以及语句
sharding.jdbc.config.props..sql.show=true
spring.main.allow-bean-definition-overriding=true
```

## 逻辑表 user ##
```
水平拆分的数据库（表）的相同逻辑和数据结构表的总称。例：用户数据根据主键尾数拆分为2张表，分别是user_0到user_1，他们的逻辑表名为user。
```

## 真实表 ##
```
在分片的数据库中真实存在的物理表。即上个示例中的user_0到user_1
```

## 分片算法: ##
**Hint分片算法**
```
对应HintShardingAlgorithm，用于处理使用Hint行分片的场景。需要配合HintShardingStrategy使用。
```

## 分片策略: ##
**行表达式分片策略**
```
对应InlineShardingStrategy。使用Groovy的表达式，提供对SQL语句中的=和IN的分片操作支持，只支持单分片键。对于简单的分片算法，可以通过简单的配置使用，从而避免繁琐的Java代码开发，如: user_$->{id % 2} 表示user表根据id模2，而分成2张表，表名称为user_0到user_1。
```

## 自增主键生成策略 ##
```
通过在客户端生成自增主键替换以数据库原生自增主键的方式，做到分布式主键无重复。
采用UUID.randomUUID()的方式产生分布式主键。或者 SNOWFLAKE
```

代码实现很简单，就像我们普通写代码一样就可以了，实现细节请看具体代码，下面是我们的测试类
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShardingSphereApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void saveBatch() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().id(1).name("张三").age(13).build());
        list.add(User.builder().id(2).name("李四").age(14).build());
        list.add(User.builder().id(3).name("王五").age(15).build());
        list.add(User.builder().id(4).name("赵柳").age(16).build());

        userService.saveBatch(list);
    }

    @Test
    public void selectAll() {
        List<User> list = userService.list();

        System.out.println(list);
    }

}
```

通过插入数据库的SQL我们可以看见数据根据id分库进入了不同的数据库，根据age进入了不同的表
![](https://i.imgur.com/kmOBRuq.png)

我们通过查询看一下数据所在的位置
![](https://i.imgur.com/sZEnZ0G.png)

好了延时到此结束，具体实现可以看代码。



### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL