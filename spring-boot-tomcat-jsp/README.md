# spring-boot-tomcat-jsp

### 说明
使用Tomcat容器执行jsp页面，spring boot版本为2.1.2.RELEASE。
在pom中引入jsp的标准库，这相对简单

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<!--JSP 标准标记库-->
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>jstl</artifactId>
	</dependency>
	<!-- Provided -->
	<dependency>
		<groupId>org.apache.tomcat.embed</groupId>
		<artifactId>tomcat-embed-jasper</artifactId>
		<scope>provided</scope>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

在编写jsp页面的时候记得要设置编码，不然中文会乱码，我在这折腾了时间
```jsp
<!DOCTYPE html>
<%--必须设置编码，不然中文会乱码--%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
    时间：${time}
	<br>
	Message: ${message}
</body>

</html>
```

在接口方便和普通接口没什么区别，直接按普通接口书写即可：
```java
/**
 * 控制接口
 * @author YI
 * @date 2019-1-26 14:37:49
 */
@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		LocalDateTime ldt = LocalDateTime.now();
		model.put("time", ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		model.put("message", this.message);
		return "welcome";
	}

	/**
	 * 统一异常处理
	 * @return
	 */
	@RequestMapping("/fail")
	public String fail() {
		throw new MyException("Oh dear!");
	}

	@ExceptionHandler(MyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody MessageResult handleMyRuntimeException(MyException exception) {
		return MessageResult.ok("你好呀");
	}

}
```

还有重要的一个就是配置文件application.properties，需要指定jsp文件的位置如下：
```
spring.mvc.view.prefix= /WEB-INF/jsp/
spring.mvc.view.suffix= .jsp
```

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL