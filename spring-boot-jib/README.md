# spring-boot-jib

### 说明
Jib 为 Java 应用程序构建优化的 Docker 和OCI映像，无需 Docker 守护进程，并且对 Docker 最佳实践没有深度掌握。它作为Maven和Gradle的插件以及 Java 库提供。

Jib 使用 Java 开发，使用也非常简单，可以作为 **Maven** 或者 **Gradle** 的插件直接集成到我们的项目中。它利用镜像分层和注册表缓存来实现快速、增量的构建。Jib 会自动读取项目的构建配置，代码组织到不同的层（依赖项、资源、类）中，然后它只会重新构建和推送发生变更的层。在项目进行快速迭代时，Jib 只将发生变更的层推送到 registers 来缩短构建时间。

这是谷歌开源的一个容器化运行方案，使用它我们将 **Spring Boot** 进行容器化部署只要两步：

- 第一步配置 Maven Plugin
- 第二步构建

### 使用
如果需要推送到官方的 **Docker Hub**,需要提前准备一个 Docker Hub 的账号，账号可以直接去 Docker Hub 上面注册（https://hub.docker.com/）

接下来我们搭建一个简单**web**工程：
我们只需要一个接口就可以了
![YnXs6x.png](https://s1.ax1x.com/2020/05/08/YnXs6x.png)

重点是在 pom.xml 中添加上 Jib 的插件，如下：
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.google.cloud.tools</groupId>
            <artifactId>jib-maven-plugin</artifactId>
            <version>2.2.0</version>
            <configuration>
                <from>
                    <image>openjdk:alpine</image>
                </from>
                <to>
                    <image>docker.io/hwyxy/hellojib</image>
                    <tags>
                        <tag>v1</tag>
                    </tags>
                    <auth>
                        <username>hwyxy</username>
                        <password>xxxxxx</password>
                    </auth>
                </to>
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>build</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**配置含义**

- 首先就是版本号的问题，我这里使用的是 2.2.0 ，网上有的教程比较老，用的 0.x 的版本，老的版本在配置 Docker 认证的时候非常麻烦，所以版本这块建议大家使用当前最新版。
- **from** 中的配置表示本镜像构建所基于的根镜像为 openjdk:alpine
- **to** 中的配置表示本镜像构建完成后，要发布到哪里去，如果是发布到私有镜像站，就写自己私有镜像站的地址，如果是发布到 Docker Hub 上，就参考我这里的写法 docker.io/hwyxy/hellojib，其中 hwyxy 表示你在 Docker Hub 上注册的用户名，hellojib 表示你镜像的名字，可以随意取(大写字母可能会出错)。
- **tags** 中配置的是自己镜像的版本。
- **auth** 中配置你在 Docker Hub 上的用户名/密码。
- **executions** 节点中的就是常规配置了，我就不再多说了。

配置完成后，在命令行执行如下命令将当前下项目构建成一个 Docker 镜像并 push 到 Docker Hub：
```
mvn compile jib:build
```
![YnjCuV.png](https://s1.ax1x.com/2020/05/08/YnjCuV.png)

上图说明我们打包推送成功了，然后我们去docker hub上去看看有没有我们的镜像。
![Ynj84H.png](https://s1.ax1x.com/2020/05/08/Ynj84H.png)

接下来，启动 Docker ，在 Docker 中执行如下命令拉取镜像下来并运行：
```
docker run -d --name mydockerjib -p 8080:8080 docker.io/hwyxy/hellojib:v1
```
访问一下，没有问题

![YnjyCj.png](https://s1.ax1x.com/2020/05/08/YnjyCj.png)

当然如果你不想推送到docker hub，你本地安装了docker，只想在本地运行也是可以的，打包命令如下：
```
mvn compile jib:dockerBuild
```
![YnjvVO.png](https://s1.ax1x.com/2020/05/08/YnjvVO.png)

我们去使用如下命令去看看本地镜像：
```
docker images
```
![Ynjr5Q.png](https://s1.ax1x.com/2020/05/08/Ynjr5Q.png)
本地启动(最后面那个参数是镜像的id)：
```
docker run -d --name mydockerjib -p 8080:8080 cf83009d2e5d
```


### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL