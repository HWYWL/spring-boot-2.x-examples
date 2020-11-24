# spring-boot-hbase

### 说明
**HBase是在Hadoop分布式文件系统HDFS之上的分布式面向列的数据库。**
本案例是使用spring boot 整合HBASE实现CRUD的操作。
比较重要的是在配置文件application.properties中需要配置几个必要参数：
```
# zookeeper集群地址
spring.data.hbase.quorum=node01:2181,node02:2181,node03:2181
# HDFS存储目录
spring.data.hbase.rootDir=/
# zookeeper子节点
spring.data.hbase.nodeParent=/hbase
```

源码显示必须配置的三个选项
![](https://i.imgur.com/LJT0w9L.png)

然后我把测试代码写在了测试类
```java
@SpringBootTest
class SpringBootHbaseApplicationTests {
    @Autowired
    HBaseService hBaseService;

    /**
     * 创建表
     *
     * @throws IOException
     */
    @Test
    void createTableTest() throws IOException {
        hBaseService.createTable("user_table", new String[]{"information", "contact"});
    }

    /**
     * 删除表
     *
     * @throws IOException
     */
    @Test
    void delteTableTest() throws IOException {
        hBaseService.deleteTable("user_table");
    }

    /**
     * 插入数据
     *
     * @throws IOException
     */
    @Test
    void insertTest() throws IOException {
        User user = new User("001", "xiaoming", "123456", "man", "20", "13355550021", "1232821@csdn.com");
        hBaseService.insertData("user_table", user);
        User user2 = new User("002", "xiaohong", "654321", "female", "18", "18757912212", "214214@csdn.com");
        hBaseService.insertData("user_table", user2);
    }

    /**
     * 获取表中所有数据
     *
     * @throws IOException
     */
    @Test
    void getAllDataTest() throws IOException {
        List<User> list = hBaseService.getAllData("user_table");
        for (User user3 : list) {
            System.out.println(user3.toString());
        }
    }

    /**
     * 获取表中原始数据
     *
     * @throws IOException
     */
    @Test
    void getNoDealDataTest() {
        ResultScanner resutScanner = hBaseService.getNoDealData("user_table");
        for (Result result : resutScanner) {
            System.out.println("scan:  " + result);
        }
    }

    /**
     * 根据rowKey查询
     *
     * @throws IOException
     */
    @Test
    void getDataByRowKeyTest() throws IOException {
        User user = hBaseService.getDataByRowKey("user_table", "user-001");
        System.out.println(user);
    }

    /**
     * 获取指定单条数据
     */
    @Test
    void getCellDataTest() {
        String userPhone = hBaseService.getCellData("user_table", "user-001", "contact", "phone");
        System.out.println(userPhone);
    }

    /**
     * 删除指定cell数据
     *
     * @throws IOException
     */
    @Test
    void deleteByRowKeyTest() throws IOException {
        hBaseService.deleteByRowKey("user_table", "001");
    }

}
```


比较简单，具体看代码吧
https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-hbase

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL