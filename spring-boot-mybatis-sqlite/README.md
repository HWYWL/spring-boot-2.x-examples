# spring-boot-mybatis-sqlite

### 说明
SQLite 是一个软件库,实现了自给自足的、无服务器的、零配置的、事务性的 SQL 数据库引擎，现在我们通过mybatis操作sqlite数据库

现在我们配置文件中配置数据库的位置信息
![](https://i.imgur.com/LaMIA11.png)

然后在我们其他操作和mybatis并没有太大的差别

配置扫描
```java
/**
 * 配置扫描接口路径
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
@MapperScan(basePackages = {"com.yi.sqlite.dao"})
public class Config {

}
```

其他代码这里就不粘贴出来了，我们直接进入测试类的测试
```java
/**
 * 添加数据
 */
@Test
public void test1() {
    String now = DateUtil.now();

    List<String> list1 = new ArrayList<>();
    list1.add("文学");
    list1.add("小说");
    String tag1 = JSONUtil.toJsonStr(list1);
    Baike baike1 = new Baike(1,"老人与海", tag1, 1000, 10, "海明威", "男", 100, 1, now, now);

    List<String> list2 = new ArrayList<>();
    list2.add("魔幻");
    list2.add("小说");
    String tag2 = JSONUtil.toJsonStr(list2);
    Baike baike2 = new Baike(2,"全职法师", tag2, 1000000, 5, "乱", "男", 1000, 1, now, now);

    baikeService.saveSelective(baike1);
    baikeService.saveSelective(baike2);
}
```
![](https://i.imgur.com/kO4Kv5Z.png)
我们可以看到数据已经进入数据库

接下来我们来看看自定义查询
```java
@Test
public void test3() {
    BaikeExample example = new BaikeExample();
    BaikeExample.Criteria criteria = example.createCriteria();

    criteria.andBookEqualTo("全职法师");

    List<Baike> baikes = baikeService.selectByExample(example);
    baikes.forEach(System.out::println);
}
```
![](https://i.imgur.com/h9ldurp.png)
我们可以看出成功查询出了数据。

具体可参考代码实现

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL