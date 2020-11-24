# spring-boot-mybatis-mutiple-datasource


### 环境
- 开发工具：IDEA
- 基础工具：Maven+JDK8
- 所用技术：SpringBoot 2.1.1.RELEASE、Mybatis 
- 数据库：MySQL 5.7

### 说明
源码：[spring-boot-mybatis-mutiple-datasource](https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-mybatis-mutiple-datasource)
基于mybatis的多数据源操作，首先我们来看一下目录结构

![](https://i.imgur.com/F5lf0Vp.jpg)

在sql目录下有两个数据源的测试SQL可以在自己的数据库中执行方便后续的测试。

然后再我们**application.properties**配置文件中配置我们数据的数据源
```
server.port=8081

# 配置第一个数据源
spring.datasource.hikari.db1.jdbc-url=jdbc:mysql://127.0.0.1:3306/baike?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8
spring.datasource.hikari.db1.username=root
spring.datasource.hikari.db1.password=
spring.datasource.hikari.db1.driver-class-name=com.mysql.cj.jdbc.Driver

# 配置第二个数据源
spring.datasource.hikari.db2.jdbc-url=jdbc:mysql://127.0.0.1:3306/script?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8
spring.datasource.hikari.db2.username=root
spring.datasource.hikari.db2.password=
spring.datasource.hikari.db2.driver-class-name=com.mysql.cj.jdbc.Driver

```

因为两个不同数据库数据源，所以我们不用使用默认的mybatis配置，需要自定义，我们定义两个配置类配置这两个数据源
**DataSource1Config**
```java
/**
 * 数据源1配置
 * @author YI
 * @date 2018-12-6 09:35:36
 */
@Configuration
@MapperScan(basePackages = "com.yi.mutiple.datasource.dao.db1", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSource1Config {

    /**
     * 生成数据源.  @Primary 注解声明为默认数据源
     */
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.db1")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用xml请放开下面配置
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/db1/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置数据库操作模板
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

**DataSource2Config**
```java
/**
 * 数据源2配置
 * @author YI
 * @date 2018-12-6 09:35:36
 */
@Configuration
@MapperScan(basePackages = "com.yi.mutiple.datasource.dao.db2", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSource2Config {

    /**
     * 生成数据源.  @Primary 注解声明为默认数据源
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.db2")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用xml请放开下面配置
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/db2/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置数据库操作模板
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

从配置中我们可以看出还可以xml方式访问，不过我们这里使用注解，如果有需要的可以打开注释即可使用xml。

接下来还有一个关键的点就是我们dao中每个dao要对应操作的数据源，可以使用**@Qualifier**注解
**BaiKeDao**
```java
/**
 * 操作baike对象
 * @author YI
 * @date 2018-12-6 09:40:19
 */
@Qualifier("db1SqlSessionTemplate")
public interface BaiKeDao {
    /**
     * 根据id查找
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM baike WHERE id = #{id}")
    Baike findBaikeById(Integer id);
}
```

**ScriptDao**
```java
/**
 * 操作Script对象
 * @author YI
 * @date 2018-12-6 09:40:19
 */
@Qualifier("db2SqlSessionTemplate")
public interface ScriptDao {
    /**
     * 根据脚本类型查找
     * @param type 脚本类型
     * @return
     */
    @Select("SELECT * FROM script_info WHERE type = #{type}")
    List<ScriptInfo> findScriptByType(Byte type);
}

```

我们使用接口请求看看我们的数据：
**BaiKeController**
![](https://i.imgur.com/aqeqwcQ.jpg)

**ScriptController**
![](https://i.imgur.com/zWYMMKa.jpg)

主要的已经讲解完毕，剩下的细节请阅读代码：[spring-boot-mybatis-mutiple-datasource](https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-mybatis-mutiple-datasource)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL