# spring-boot-thymeleaf
thymeleaf 模板引擎

### 说明
一个完整的实例

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>用户列表</title>
</head>
<body>
<h1 th:text="${hello}">Hello, Spring Boot!</h1>
<table>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>生日</th>
        <th>薪资</th>
    </tr>
    <tr th:each="user : ${userList}">
        <td th:text="${user.id}">0</td>
        <td th:text="${user.name}">美女</td>
        <td th:text="${user.age}">18</td>
        <td th:text="${user.salary}">12345.50</td>
    </tr>
</table>

<select>
    <option th:each="user:${userList}" th:value="${user.id}" th:text="${user.name}">我是默认值</option>
</select>

</body>
</html>
```

后端：
```java
/**
 * 调用前端模板并返回数据
 *
 * @author YI
 * @date 2018-10-14 20:11:26
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("hello", "Hello, Spring Boot And Thymeleaf!");

        List<User> list = new ArrayList<>();

        User user1 = new User(1, "美女", 18, 12000.50);
        User user2 = new User(2, "校花", 20, 10000.50);
        User user3 = new User(3, "小萝莉", 16, 11000.50);

        list.add(user1);
        list.add(user2);
        list.add(user3);

        map.addAttribute("userList", list);

        return "index";
    }
}
```

服务器启动后访问：http://localhost:8080
![](https://i.imgur.com/pK4mIjU.png)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL