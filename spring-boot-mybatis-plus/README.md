# spring-boot-mybatis-plus

### 说明
spring boot在操作数据库时，推荐使用hikari连接池，所以他内置了，这样不需要不要我们在引入其他maven依赖，
我们只需要在噢诶指纹键中配置即可使用。我们使用mybatis-plus进行简单的CURD

```java
@Test
public void contextLoads() {
    List<Baike> baikes = baiKeService.list();
    System.out.println(baikes);

    IPage<Baike> page = baiKeService.page(new Page<>(0, 1), null);
    System.out.println(page.getRecords());

    List<Baike> list = baiKeService.selectListBySQL();
    System.out.println(list);

    List<Baike> baikeList = baiKeService.selectListByWrapper(new QueryWrapper<Baike>().eq("name", "乱"));
    System.out.println(baikeList);
}
```
![](https://i.imgur.com/WskyCuV.jpg)

我们在mapper中可以加入多重语句的执行语法
```java
/**
 * 操作数据接口
 * @author YI
 * @date 2018-8-22 17:48:27
 */
public interface BaikeMapper extends BaseMapper<Baike> {
    /**
     * 通过注解sql查询
     * @return
     */
    @Select("SELECT * FROM `baike` WHERE bad > 5")
    List<Baike> selectListBySQL();

    /**
     * 根据条件查询
     * @param wrapper 组合条件
     * @return
     */
    List<Baike> selectListByWrapper(@Param("ew") Wrapper wrapper);
}
```
具体可参考代码实现

更多的语法可以自行查阅官方文档
 http://mp.baomidou.com/guide/

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL