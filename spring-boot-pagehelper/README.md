# spring-boot-pagehelper

### 说明
spring boot在操作数据库时，我们使用分页插件来对数据查询进行分页，使用**PageHelper**插件分页非常简单

### 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

<!--通用mapper-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
<!--分页-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.10</version>
</dependency>
```
这是主要的依赖

然后我们分页只需要一句话即可，是不是简单到令人发指
```java
/**
 * 查找所有数据
 * @param page 当前页
 * @param size 页大小
 * @return
 */
@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
public MessageResult selectAll(@RequestParam(value = "page",defaultValue = "1")int page,
                               @RequestParam(value = "size",defaultValue = "10")int size){

    // 分页查询
    PageHelper.startPage(page, size);
    List<Baike> videos = baikeMapper.selectAll();

    return MessageResult.ok(videos);
}
```

我们来看看查询的效果

![](https://i.imgur.com/vIAXMzx.png)

具体可参考代码实现

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL