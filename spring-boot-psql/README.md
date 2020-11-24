# spring-boot-psql

### 说明
PostgreSQL是一种特性非常齐全的自由软件的对象-关系型数据库管理系统,支持大部分的SQL标准。spring boot在操作数据库时，推荐使用hikari连接池，所以他内置了，这样不需要不要我们在引入其他maven依赖，我们只需要在application.properties中配置即可使用，此项目我们使用mybatis操作PostgreSQL。

PostgreSQL连接配置：
```
# PostgresSQL
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/gamedb_test
spring.datasource.username=postgres
spring.datasource.password=root
```

连接池配置：
```
# hikari连接池，默认自带无需引入pom
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=15
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.pool-name=DatebookHikariCP
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.connection-test-query=SELECT 1
```

其实使用mybatis之后并且PostgreSQL支持标准的sql语，所以我们使用的时候和MySQL并没用很大的区别。
需要注意的是我们还是需要在代码中配置mapper包的路径：
```java
/**
 * 配置扫描接口路径
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
@MapperScan(basePackages = {"com.yi.psql.mapper"})
public class Config {

}
```

因为我是使用xml来写的SQL，所以还需要在配置文件中指定保存xml目录所在的位置：
```
#model 自动扫描
mybatis.type-aliases-package=com.yi.psql.model
mybatis.mapper-locations=classpath:mapper/*.xml
```

具体可参考代码实现，这里就不累述了，测试代码在：
```
spring-boot-psql/src/test/java/com/yi/psql/SpringBootPsqlApplicationTests.java
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL