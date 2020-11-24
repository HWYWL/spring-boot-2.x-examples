# spring-boot-auto-code-ui

### 说明
强大的代码生成器，只需要极少的配置可生成 单表、一对一、一对多、多对多代码，支持无限级联。

我们新建一个spring boot工程，在pom.xml中添加主要的配置
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>com.zengtengpeng</groupId>
        <artifactId>auto-code-ui</artifactId>
        <version>1.0.2</version>
    </dependency>
    <!-- pagehelper 分页插件 -->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>1.2.10</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

在application.properties配置文件中添加我们的配置
```
#数据库连接
spring.datasource.name=auto-code
spring.datasource.url=jdbc:mysql://localhost:3306/auto-code?allowMultiQueries=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&zeroDateTimeBehavior=convertToNull
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#pagehelper插件
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql

#代码生成器配置
#生成代码的项目根路径
auto-code.parentPath=E:\\resource\\workspaceJDB\\auto-code-springboot-demo
#生成代码的父包 如父包是com.yi.auto.code.ui  controller将在com.yi.auto.code.ui.controller下 bean 将在com.yi.auto.code.ui.bean下 ,service,dao同理
auto-code.parentPack=com.yi.auto.code.ui
```

随后编写我们的配置类请看**com.yi.auto.code.ui.config**配置文件

启动我们的程序访问：http://localhost:8080/auto-code-ui/ui/index.html 即可使用

![](https://i.imgur.com/deWuPxx.png)

这是作者给出的文档

[点击查看文档](https://gitee.com/ztp/auto-code/blob/master/README.md)


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL