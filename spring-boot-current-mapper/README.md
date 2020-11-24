# spring-boot-current-mapper

### 说明
通用mapper

```java
@Test
public void contextLoads() {
    // 注解
    List<Baike> baikes = baikeMapper.selectListBySQL(5);
    System.out.println(baikes);

    // xml
    List<Baike> book = baikeMapper.selectByBookName("全职法师");
    System.out.println(book);

    // 通用
    List<Baike> selectAll = baikeMapper.selectAll();
    System.out.println(selectAll);

    // 自定义条件
    Example example = new Example(Baike.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("name", "海明威");
    List<Baike> list = baikeMapper.selectByExample(example);
    System.out.println(list);
}
```
![](https://i.imgur.com/YriIims.jpg)

我们在mapper中可以加入多种语句的执行语法
```java
/**
 * 操作数据接口
 * @author YI
 * @date 2018-11-27 15:51:01
 */
public interface BaikeMapper extends Mapper<Baike> {
    /**
     * 通过注解sql查询
     * @return
     */
    @Select("SELECT * FROM `baike` WHERE bad > #{bad}")
    List<Baike> selectListBySQL(int bad);

    /**
     * 通过xml访问数据库（不推荐方式）
     * @param book
     * @return
     */
    List<Baike> selectByBookName(String book);
}
```
具体可参考代码实现

更多的语法可以自行查阅官方文档
 https://github.com/abel533/Mapper/wiki

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL