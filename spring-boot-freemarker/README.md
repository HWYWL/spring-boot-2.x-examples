# spring-boot-freemarker
Freemarker 模板引擎

### 说明
Freemarker 使用就像我们使用普通的html一样，相对于thymeleaf较为简单，不需要tx标签


一个完整的实例

```
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8" />
        <title>Freemarker</title>
    </head>

    <body>
        <h1>${say}</h1>
    </body>
</html>
```

后端：
```
/**
 * 调用前端模板并返回数据
 * @author YI
 * @date 2018-8-21 09:50:09
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("say", "Hello World And Freemarker");

        return "index";
    }
}
```

服务器启动后访问：http://localhost:8080
![](https://i.imgur.com/pHdKqZi.jpg)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL