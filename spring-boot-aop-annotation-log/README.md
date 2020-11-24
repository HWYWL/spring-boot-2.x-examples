# spring-boot-aop-annotation-log

### 说明
使用Spring的AOP进行对日志的增强操作，使用注解拦截我们的接口。
```java
/**
 * 注解apo拦截
 *
 * @author YI
 * @date 2019年4月29日16:33:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 接口描述
     * @return
     */
    String description() default "";
}
```

在我们访问接口时，通过aop的环绕通知进行环绕增强。
```java
/**
 * 用户登录
 * @param user
 * @return
 */
@RequestMapping(value = "/user/login", method = RequestMethod.POST)
@ResponseBody
@WebLog(description = "用户注册")
public MessageResult login(@RequestBody User user) {
    if (user == null || StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
        logger.info("error : 用户名或者密码不能为空");
        return MessageResult.errorMsg("用户名或者密码不能为空");
    }

    if (!user.getUsername().equals(USERNAME)){
        logger.info("error : 用户或密码不正确");
        return MessageResult.errorMsg("用户或密码不正确");
    }

    if (!user.getPassword().equals(PASSWORD)){
        logger.info("error : 用户或密码不正确");
        return MessageResult.errorMsg("用户或密码不正确");
    }

    return MessageResult.ok("登录成功！！！");
}
```
![](https://i.imgur.com/lkjmzJc.png)

比较简单，具体看代码吧

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL