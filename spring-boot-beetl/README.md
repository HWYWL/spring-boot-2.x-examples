# spring-boot-beetl
Beetl目前版本是2.9.3,相对于其他java模板引擎，具有功能齐全，语法直观,性能超高，以及编写的模板容易维护等特点。使得开发和维护模板有很好的体验。是新一代的模板引擎。

### 说明
特性如下：

- 功能完备：作为主流模板引擎，Beetl具有相当多的功能和其他模板引擎不具备的功能。适用于各种应用场景，从对响应速度有很高要求的大网站到功能繁多的CMS管理系统都适合。Beetl本身还具有很多独特功能来完成模板编写和维护，这是其他模板引擎所不具有的。

- 非常简单：类似Javascript语法和习俗，只要半小时就能通过半学半猜完全掌握用法。拒绝其他模板引擎那种非人性化的语法和习俗。同时也能支持html 标签，使得开发CMS系统比较容易

- 超高的性能：Beetl 远超过主流java模板引擎性能(引擎性能5-6倍于FreeMarker，2倍于JSP。参考附录），而且消耗较低的CPU。

- 易于整合：Beetl能很容易的与各种web框架整合，如Spring MVC，JFinal，Struts，Nutz，Jodd，Servlet等。

- 支持模板单独开发和测试，即在MVC架构中，即使没有M和C部分，也能开发和测试模板。

- 扩展和个性化：Beetl支持自定义方法，格式化函数，虚拟属性，标签，和HTML标签. 同时Beetl也支持自定义占位符和控制语句起始符号也支持使用者完全可以打造适合自己的工具包。

- 可以扩展为脚本引擎，规则引擎，能定制引擎从而实现高级功能。

### 代码
```java
/**
 * 模板引擎测试接口
 * @author YI
 * @date 2019-3-1 09:36:50
 */
@Controller
public class BeetlController {

    @GetMapping({"/","/index","/beetl"})
    public String beetl(Model model){
        model.addAttribute("beetl","你好，Beetl模板引擎");

        return "index.html";
    }
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试页面</title>
</head>

<body>
    <span>通过beetl模板引擎自动渲染</span>
    <h1>${beetl}</h1>
</body>
</html>
```

### 执行结果
![](https://i.imgur.com/V4RMAE6.png)

更多复杂的操作，请参考官方文档：http://ibeetl.com/guide/#beetl


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL