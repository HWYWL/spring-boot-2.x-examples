# spring-boot-mybatis-hikari

### 说明
spring boot在操作数据库时，推荐使用hikari连接池，所以他内置了，这样不需要不要我们在引入其他maven依赖，
我们只需要在噢诶指纹键中配置即可使用。

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

具体可参考代码实现

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL