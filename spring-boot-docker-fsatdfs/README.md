# spring-boot-docker-fsatdfs
通过Docker构建FastDFS文件系统

## 1\. 获取镜像

可以利用已有的FastDFS Docker镜像来运行FastDFS。

获取镜像可以通过下载

    docker image pull delron/fastdfs

也可是直接使用提供的镜像备份文件

    docker load -i 文件路径/fastdfs_docker.tar（提前打包好的）

加载好镜像后，就可以开启运行FastDFS的tracker和storage了。

## <a name="t1" style="outline-style: initial; outline-width: 0px; color: rgb(78, 161, 219); cursor: pointer; word-break: break-all;"></a>2\. 运行tracker

执行如下命令开启tracker 服务

    docker run -dti --network=host --name tracker -v /var/fdfs/tracker:/var/fdfs delron/fastdfs tracker

*   我们将fastDFS tracker运行目录映射到本机的 /var/fdfs/tracker目录中。

执行如下命令查看tracker是否运行起来

    docker container ls

如果想停止tracker服务，可以执行如下命令

    docker container stop tracker

停止后，重新运行tracker，可以执行如下命令

    docker container start tracker

## <a name="t2" style="outline-style: initial; outline-width: 0px; color: rgb(78, 161, 219); cursor: pointer; word-break: break-all;"></a>3\. 运行storage

执行如下命令开启storage服务

    docker run -dti --network=host --name storage -e TRACKER_SERVER=192.168.99.100:22122 -v /var/fdfs/storage:/var/fdfs delron/fastdfs storage

*   TRACKER_SERVER=本机的ip地址:22122 本机ip地址不要使用127.0.0.1
*   我们将fastDFS storage运行目录映射到本机的/var/fdfs/storage目录中

执行如下命令查看storage是否运行起来

    docker container ls

如果想停止storage服务，可以执行如下命令

    docker container stop storage

停止后，重新运行storage，可以执行如下命令

    docker container start storage

<span style="outline-style: initial; outline-width: 0px; word-break: break-all; font-weight: 700;">注意：如果无法重新运行，可以删除`/var/fdfs/storage/data`目录下的`fdfs_storaged.pid` 文件，然后重新运行storage。</span>

<span style="outline-style: initial; outline-width: 0px; word-break: break-all; font-weight: 700;"><span style="letter-spacing: 0.2px;"><font color="#ff0000">警告：我此处使用的是win10安装的docker，如果你使用云服务器，记得在安全组开放22122和8888这两个端口，否则将代码将无法连接。</font></span></span>

使用七牛或者阿里OSS记得到配置文件中修改key等参数。


<span style="outline-style: initial; outline-width: 0px; word-break: break-all; font-weight: 700;">项目地址：[https://github.com/HWYWL/spring-boot-docker-fsatdfs](https://github.com/HWYWL/spring-boot-docker-fsatdfs)</span>

<span style="outline-style: initial; outline-width: 0px; word-break: break-all; font-weight: 700;">效果图：</span>
![](https://i.imgur.com/85uaJ0d.png)

<span style="outline-style: initial; outline-width: 0px; word-break: break-all; font-weight: 700;"><span style="letter-spacing: 0.2px;"><font color="#ff0000">有没有眼尖的同学发现FastDFS拼错了,我就不改了</font></span></span>
![](https://i.imgur.com/vMe3i6I.jpg)