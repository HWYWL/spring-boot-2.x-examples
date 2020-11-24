# spring-boot-solr

### 说明
下载安装Apache Solr 7.3.1
下载地址：http://archive.apache.org/dist/lucene/solr/7.3.1/
![](https://i.imgur.com/wq4835Z.png)
解压后，在bin文件夹下编写一个名为 启动.bat的启动文件，把一下命令粘贴进去
```
solr.cmd start -p 8888
```
![](https://i.imgur.com/MGahKjM.png)

到这里一般双击就可以启动了
![](https://i.imgur.com/9RP8mxS.png)

访问http://localhost:8888
![](https://i.imgur.com/9Ojl3sx.png)

接下来我们新建一个core用于存储我们的数据
![](https://i.imgur.com/1pOoWaG.png)
我们会发现无发创建core
```
solr-7.3.1/server/solr/configsets/_default/
此时我们需要拷贝以上路径下的conf文件夹到以下路径中
solr-7.3.1/server/solr/baike_core
```

关闭命令窗口。重新双击启动，然后我们就可以创建这个core了
![](https://i.imgur.com/fQ2kwLt.png)

我们关闭命令窗口，虽然可以使用，不过我们还需要配置一下中文分词器，没分词之前
![](https://i.imgur.com/jasGf5k.png)

为了方便中文分词，我们这里使用ik-analyzer分词器
下载地址：http://central.maven.org/maven2/com/github/magese/ik-analyzer/7.4.0/ik-analyzer-7.4.0.jar
把下载的jar包复制到以下路径
```
solr-7.3.1/server/solr-webapp/webapp/WEB-INF/lib
```
![](https://i.imgur.com/1KUcb5T.png)

然后到以下目录中打开managed-schema文件
```
solr-7.3.1/server/solr/baike_core/conf
```
在后面加上以下配置代码
```
<!-- ik分词器 -->
<fieldType name="text_ik" class="solr.TextField">
  <analyzer type="index">
      <tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" useSmart="false" conf="ik.conf"/>
      <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
  <analyzer type="query">
      <tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" useSmart="true" conf="ik.conf"/>
      <filter class="solr.LowerCaseFilterFactory"/>
  </analyzer>
</fieldType>
```
![](https://i.imgur.com/7rYbKKl.png)

我们重新启动solr，再分词测试
![](https://i.imgur.com/d8yr3ys.png)

好了接下来就是写测试代码了,运行效果：
![](https://i.imgur.com/uG9F7Aa.jpg)

代码：https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-solr

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL