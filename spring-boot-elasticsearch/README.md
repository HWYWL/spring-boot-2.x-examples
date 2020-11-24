# spring-boot-elasticsearch

### 说明
我发现很多都是基于Spring Boot 1.5 的，基于2.0以后我没找到，而且网上也有很多无法使用，故记录下此篇文章，希望能帮助你。

# 环境
- Windows 7
- Spring Boot 2.0.4.RELEASE
- [ElasticSearch 5.5.2](https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.5.2.zip)
- [elasticsearch-analysis-ik 5.5.2](https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v5.5.2/elasticsearch-analysis-ik-5.5.2.zip)



# 安装
把下载的ElasticSearch 5.5.2解压到没有**空格的路径**中，把elasticsearch-analysis-ik解压到plugins中的analysis-ik（需要自己手动创建）文件夹下，具体可以看如下图片。
![](https://i.imgur.com/QmvkeSu.jpg)

因为我使用的是集群部署，所以可以把elasticsearch-5.5.2复制三份，分别改名为如下
![](https://i.imgur.com/PdVYago.jpg)

# 配置
打开elasticsearch-5.5.2-noed1\config下的elasticsearch.yml文件，删除所有数据改为如下
```js
#节点1的配置信息：  
cluster.name: elasticsearch   #集群名称，保证唯一  
node.name: Elasticsearch-node1   #节点名称，必须不一样  
network.host: 192.168.1.145   #必须为本机的ip地址  
http.port: 9200   #服务端口号，在同一机器下必须不一样  
transport.tcp.port : 9300   #集群间通信端口号，在同一机器下必须不一样  
#设置集群自动发现机器ip集合  
discovery.zen.ping.unicast.hosts: ["192.168.1.145:9300", "192.168.1.145:9301", "192.168.1.145:9302"]
```  
当然ip地址得用你的，**别用localhost和127.0.0.1**，以下修改也一样

打开elasticsearch-5.5.2-noed2\config下的elasticsearch.yml文件，删除所有数据改为如下
```js
#节点2的配置信息：  
cluster.name: elasticsearch   #集群名称，保证唯一  
node.name: Elasticsearch-node2   #节点名称，必须不一样  
network.host: 192.168.1.145   #必须为本机的ip地址  
http.port: 9201   #服务端口号，在同一机器下必须不一样  
transport.tcp.port : 9301   #集群间通信端口号，在同一机器下必须不一样  
#设置集群自动发现机器ip集合  
discovery.zen.ping.unicast.hosts: ["192.168.1.145:9300", "192.168.1.145:9301", "192.168.1.145:9302"]
```  
打开elasticsearch-5.5.2-noed3\config下的elasticsearch.yml文件，删除所有数据改为如下
```js
#节点3的配置信息：  
cluster.name: elasticsearch   #集群名称，保证唯一  
node.name: Elasticsearch-node3   #节点名称，必须不一样  
network.host: 192.168.1.145   #必须为本机的ip地址  
http.port: 9202   #服务端口号，在同一机器下必须不一样  
transport.tcp.port : 9302   #集群间通信端口号，在同一机器下必须不一样  
#设置集群自动发现机器ip集合  
discovery.zen.ping.unicast.hosts: ["192.168.1.145:9300", "192.168.1.145:9301", "192.168.1.145:9302"]
```  
# 启动
```js
进入elasticsearch-5.5.2-noed1\bin\elasticsearch.bat 双击启动
进入elasticsearch-5.5.2-noed2\bin\elasticsearch.bat 双击启动
进入elasticsearch-5.5.2-noed3\bin\elasticsearch.bat 双击启动
```  
![](https://i.imgur.com/ZYWKJY4.jpg)
如果启动界面没报错一般就启动成功了。

# 数据查看
因为我的保存过数据，所以和新安装的可能不太一样，但没事能见见就证明成功了，使用ElasticSearch Head，
如果你能翻墙，恭喜谷歌浏览器有个简单的安装方法，进入谷歌应用商店
![](https://i.imgur.com/bxD3Xjp.jpg)

如果你不能翻墙或者没使用谷歌浏览器，那就自行百度吧
![](https://i.imgur.com/8ElA7du.jpg)

在ElasticSearch Head中填上ip地址，点击连接就可查看到数据
![](https://i.imgur.com/HhHj2dz.jpg)
我存入的数据
![](https://i.imgur.com/cmQNsre.jpg)
# Spring Boot集成操作
实现了保存，简单查询，按条件查询，权重。。。。。。

代码：[spring-boot-elasticsearch](https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-elasticsearch)

如果你懒得配置elasticsearch，可以到我上传的百度云这里下载，下载下来记得改一下配置文件的ip地址就可以使用了

链接：https://pan.baidu.com/s/1rgJ0VxOIWsDEXcJGXrkMpg 密码：uaw7

# 总结
打字好累
![](https://i.imgur.com/NA6Uji2.gif)


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL