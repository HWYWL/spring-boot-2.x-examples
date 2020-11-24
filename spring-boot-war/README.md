# spring-boot-war
将Spring Boot打包成war包

### 说明
其实只需要将我们pom.xml中的jar改为war即可，然后通过命令打包
```
mvn package
```

![](https://i.imgur.com/CxzU8BM.jpg)

**打包结果**

![](https://i.imgur.com/PARP10o.jpg)

**打包出来的war文件**

![](https://i.imgur.com/QR7skQc.jpg)

复制war包到Tomcat的webapp目录下，启动Tomcat，Tomcat启动后访问：http://localhost:8080
![](https://i.imgur.com/RJg3hs1.jpg)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL