# spring-boot-jetty

### 说明
替换掉默认的Tomcat容器换为jetty，其实很简单，我们只需要在pom依赖中修改即可，它不影响spring的使用，不需要更改代码。


```xml
<dependencies>
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-web</artifactId>
	    <exclusions>
	        <!--排除掉Tomcat-->
	        <exclusion>
	            <groupId>org.springframework.boot</groupId>
	            <artifactId>spring-boot-starter-tomcat</artifactId>
	        </exclusion>
	    </exclusions>
	</dependency>
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-jetty</artifactId>
	</dependency>
	
	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-test</artifactId>
	    <scope>test</scope>
	</dependency>
</dependencies>
```

可以做到无缝切换。

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL