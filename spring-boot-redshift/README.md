# spring-boot-redshift

### 说明
Amazon Redshift 是一种快速、完全托管的 PB 级数据仓库服务，它使得用现有商业智能工具对您的所有数据进行高效分析变得简单而实惠。它为从几百 GB 到 1PB 或更大的数据集而优化，且每年每 TB 花费不到 1 000 USD，为最传统数据仓库存储解决方案成本的十分之一。

**使用maven的时候需要设置亚马逊的仓库地址**
```
<!--亚马逊Redshift maven-->
<repositories>
    <repository>
        <id>redshift</id>
        <url>http://redshift-maven-repository.s3-website-us-east-1.amazonaws.com/release</url>
    </repository>
</repositories>
```

Redshift 连接配置：
```
# Redshift 
spring.datasource.driverClassName = com.amazon.redshift.jdbc.Driver
spring.datasource.url = jdbc:redshift://realtime-data.aaaaaaa.cn-northwest-1.redshift.amazonaws.com.cn:5439/realtimedb
spring.datasource.username = root
spring.datasource.password = root
```

连接池配置（可选）：
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

其实使用mybatis之后并且Redshift大部分支持标准的sql语，所以我们使用的时候和PostgreSQL并没用很大的区别。
具体的区别可以看这里的文档：[Amazon Redshift 和 PostgreSQL](https://docs.aws.amazon.com/zh_cn/redshift/latest/dg/c_redshift-and-postgres-sql.html)

需要注意的是我们还是需要在代码中配置mapper包的路径：
```java
/**
 * 配置扫描接口路径
 * @author YI
 * @date 2019-12-20
 */
@Configuration
@MapperScan(basePackages = {"com.yi.redshift.mapper"})
public class Config {

}
```

具体可参考代码实现，这里就不累述了，测试代码在：
```
com.yi.redshift.SpringBootRedshiftApplicationTests
```

当然在使用之前建议看看这个文档：[Amazon Redshift 最佳实践](https://docs.aws.amazon.com/zh_cn/redshift/latest/dg/best-practices.html)

**注意**
在建表的时候最好不要使用Amazon Redshift 保留关键字，否则将无法使用orm框架提供的CRUD	。如果必须使用，请手写SQL并把关键字用双引号括起来，如下 **tag** 字段：
```
@Insert("INSERT INTO baike(id, book, \"tag\", good, bad, name, gender, gold_coin, status, crate_date, update_date) " +
        "VALUES (#{id}, #{book}, #{tag}, #{good}, #{bad}, #{name}, #{gender}, #{goldCoin}, #{status}, #{crateDate}, #{updateDate})")
int insertBaike(Baike baike);
```

[点击查看保留关键字的列表](https://docs.amazonaws.cn/redshift/latest/dg/r_pg_keywords.html)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL
