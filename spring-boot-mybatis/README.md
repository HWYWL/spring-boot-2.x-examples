# spring-boot-mybatis

### 说明
运行程序之前请先根据sql文件中的sql语句导入你的数据库，并更改application.properties的数据库连接

使用spring-boot-mybatis操作数据库，并加入了Example条件操作

example实例解析
mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分 
```
xxxExample example = new xxxExample(); 
Criteria criteria = new Example().createCriteria();
```
![](https://i.imgur.com/sGw6xym.jpg)

具体用法可以查看测试用例，举一反三

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL