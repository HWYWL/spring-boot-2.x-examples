# spring-boot-excel

### 说明
我们使用王爵大佬开发的工具包实现excel的操作，它里面封装了大量的api，对原有的poi的接口做了大量的简化，非常好用
```xml
<dependency>
    <groupId>io.github.biezhi</groupId>
    <artifactId>excel-plus</artifactId>
    <version>1.0.4</version>
</dependency>
```
这是目前最新的版本

实例代码比较简单，只做了一个从浏览器下载的功能，具体看代码吧
```java
/**
 * 下载excel
 * @return excel下载
 */
@RequestMapping("/download")
public void download(HttpServletResponse servletResponse) throws WriterException {
    List<Book> books = new ArrayList<>();
    books.add(new Book("新名字的故事", "埃莱娜·费兰特", 59.0D, LocalDate.of(2017, 4, 1)));
    books.add(new Book("鱼王", "Царь-рыба", 78.0D, LocalDate.of(2017, 4, 1)));
    books.add(new Book("不可思议的朋友", "[日] 田岛征彦", 45.0D, LocalDate.of(2017, 7, 1)));
    books.add(new Book("杀死一只知更鸟", "[美] 哈珀·李", 48.0D, LocalDate.of(2017, 2, 1)));
    books.add(new Book("现代艺术150年", " [英] 威尔·贡培兹", 65.0D, LocalDate.of(2017, 3, 1)));

    Writer.create()
            .withRows(books)
            .headerTitle("书籍列表 V1")
            .to(ResponseWrapper.create(servletResponse ,"book.xlsx"));
}
```
![](https://i.imgur.com/ZprA2x0.png)
是不是超级简单就能得到一个excel表格，下面是图示是下载后的excel文件
![](https://i.imgur.com/bOaAvN7.png)

我们读取execl的数据并显示在表格上
```java
/**
 * 读取excel展示
 * @return excel数据
 */
@RequestMapping("/show")
@ResponseBody
public MessageResult show() {
    String path = IndexController.class.getResource("/").getPath();
    List<Sample> samples = Reader.create(Sample.class)
            .from(new File(path + "/SampleData.xlsx"))
            .sheet("SalesOrders")
            .start(1)
            .asList();

    return MessageResult.ok(samples);
}
```
![](https://i.imgur.com/0YbvhDN.png)

这个工具包有很多强大的功能，下面是他的文档，有需要的可以认真看看
https://biezhi.github.io/excel-plus

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL