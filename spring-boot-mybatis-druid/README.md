# spring-boot-mybatis-druid

### 说明
运行程序之前请先根据sql文件中的sql语句导入你的数据库，并更改application.properties的数据库连接

使用spring-boot-mybatis操作数据库，并加入了Example条件操作

example实例解析
mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分 
```
xxxExample example = new xxxExample(); 
Criteria criteria = new Example().createCriteria();
```
![](https://i.imgur.com/sGw6xym.jpg)

具体用法可以查看测试用例，举一反三

在项目中我们使用了阿里的druid连接池，使用非常简单的。

首先引入它的maven
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
```

然后在 application.properties 配置文件配置基础信息

```
# 初始化大小，最小，最大链接数
spring.datasource.druid.initial-size=3
spring.datasource.druid.min-idle=3
spring.datasource.druid.max-active=10

# 配置获取连接等待超时的时间
spring.datasource.druid.max-wait=60000

#  监控后台的账户和密码
spring.datasource.druid.stat-view-servlet.login-username=admin
spring.datasource.druid.stat-view-servlet.login-password=admin

# 配置StatFilter
spring.datasource.druid.filter.stat.log-slow-sql=true
spring.datasource.druid.filter.stat.slow-sql-millis=2000
```

好了它已经完成了，我们把服务器启动，访问 http://localhost:8080/druid/index.html

![](https://i.imgur.com/kCiCfuS.jpg)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL