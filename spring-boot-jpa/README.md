# spring-boot-jpa

### 说明
Spring Data JPA 是Spring Data 的一个子项目，它通过提供基于JPA的Repository极大了减少了操作JPA的代码。笔者觉得这个由SpringBoot 提供的JPARepository真的是非常爽，基本上大部分的业务都可以满足了，666。

我们先引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

然后在application.properties配置一下我们需要的参数
```
# MySQL
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/baike?useUnicode=true&characterEncoding=utf-8
spring.datasource.username = root
spring.datasource.password =

spring.datasource.tomcat.max-active=20
spring.datasource.tomcat.test-while-idle=true
spring.datasource.tomcat.validation-query=select 1
spring.datasource.tomcat.default-auto-commit=false
spring.datasource.tomcat.min-idle=15
spring.datasource.tomcat.initial-size=15

#jpa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jackson.serialization.indent-output=true
```

然后是我们的bean
```
package com.yi.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 百科
 * @author YI
 * @date 2018-8-22 18:28:19
 */
@Entity
@Table(name = "baike")
public class Baike implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	// 其他参数看源码
}
```

### 强大的Spring JpaRepository
SpringBoot创建DAO层很多种方法其中japrepository是最强大的而且最有特色的一种，我们可以针对不同的实体创建repository接口。Spring会根据方法名称的规则进行自动生成实现，强大的不要不要的。在SpringBoot中默认已经提供了非常多的常规CRUD操作的repository。
有兴趣可以看看一下文件的源码：
```
JpaRepository.java
PagingAndSortingRepository.java
CrudRepository.java
```
这些源码实现的操作完全不用我们再去实现，这些不是我们以往在普通的Spring项目中自己所定义的BaseDao吗？SpringBoot真的是非常体贴，大大减低了我们的工作量。但是更为强大的还在后面。我们通过继承JpaRepository接口，除了可以获得上面的基础CRUD操作方法之外，还可以通过Spring规定的接口命名方法自动创建复杂的CRUD操作，我们可以百度Spring Data JPA 的命名规则表找到命名规则。

在我的代码中有两个写法，一个是直接使用jpa的操作方式，
另一个是使用HQL语法的操作方式，可以自由选择。

### JpaSpecificationExecutor接口
还记得刚刚暂时忽略的JpaSpecificationExecutor<Baike>接口继承吗？在BaikeHQLRepository接口中继承JpaSpecificationExecutor<Baike>接口就可以在外部使用Spring提供的Criteria查询，但是这个查询也存在一些问题，具体我就不展示了。


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL