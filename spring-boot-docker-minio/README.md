# spring-boot-docker-minio

## 说明
MinIO 是一个基于Apache License v2.0开源协议的对象存储服务。它兼容亚马逊S3云存储服务接口，非常适合于存储大容量非结构化的数据，例如图片、视频、日志文件、备份数据和容器/虚拟机镜像等，而一个对象文件可以是任意大小，从几kb到最大5T不等。

MinIO是一个非常轻量的服务,可以很简单的和其他应用的结合，类似 NodeJS, Redis 或者 MySQL。

## 安装启动
### docker安装
MinIO 需要一个持久卷来存储配置和应用数据。不过, 如果只是为了测试一下, 您可以通过简单地传递一个目录（在下面的示例中为/ data）启动MinIO。

![](https://hwy-figure-bed.oss-cn-hangzhou.aliyuncs.com/image/20200812142812.png)

然后浏览器访问**http:127.0.0.1:9000**就可以看见主页面了

![](https://hwy-figure-bed.oss-cn-hangzhou.aliyuncs.com/image/20200812142934.png)

这个目录会在容器启动时在容器的文件系统中创建，不过所有的数据都会在容器退出时丢失。
```
docker run -p 9000:9000 minio/minio server /data
```

要创建具有永久存储的MinIO容器，您需要将本地持久目录从主机操作系统映射到虚拟配置**~/.minio** 并导出**/data**目录。 为此，请运行以下命令

**GNU/Linux 和 macOS**
```
docker run -p 9000:9000 --name minio1 \
  -v /mnt/data:/data \
  -v /mnt/config:/root/.minio \
  minio/minio server /data
```

**Windows**
要覆盖MinIO的自动生成的密钥，您可以将Access和Secret密钥设为环境变量。 MinIO允许常规字符串作为Access和Secret密钥。

```
docker run -p 9000:9000 --name minio1 \
  -v D:\data:/data \
  -v D:\minio\config:/root/.minio \
  minio/minio server /data
```

### MinIO自定义Access和Secret密钥
**GNU/Linux 和 macOS**
```
docker run -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=hwy" \
  -e "MINIO_SECRET_KEY=123456" \
  -v /mnt/data:/data \
  -v /mnt/config:/root/.minio \
  minio/minio server /data
```

**Windows**
```
docker run -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=hwy" \
  -e "MINIO_SECRET_KEY=123456" \
  -v D:\data:/data \
  -v D:\minio\config:/root/.minio \
  minio/minio server /data
```

更具体的文档可以查阅官方文档：https://docs.min.io/cn/

## 代码
MiniO提供了JavaScript、Java、Python、Golang、.NET几种语言的SDK，除此之外你甚至还可以使用cli控制台或者AWS SDK进行操作。

这里使用Java进行演示。

引入pom
```
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>3.0.10</version>
</dependency>
```

**初始化**：
```
@Data
@Configuration
@ConfigurationProperties(prefix = "minio.dfs")
public class MiniOConfig {

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

    /**
     * 文件存储桶
     */
    public String bucket;

    @Bean
    public MinioClient minioClientFactory() throws Exception {

        return new MinioClient(endpoint, accessKey, secretKey);
    }
}
```

**文件上传并拉取文静列表**：
```
@RestController
@RequestMapping("/minio")
public class MiniOController {
    @Autowired
    MiniOConfig miniOConfig;

    @Autowired
    MinioClient minioClient;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    public MessageResult singleFileUpload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        minioClient.putObject(miniOConfig.bucket, fileName, file.getInputStream(), file.getSize(), file.getContentType());

        Iterable<Result<Item>> results = minioClient.listObjects(miniOConfig.bucket);
        List<Item> files = new ArrayList<>();
        for (Result<Item> result : results) {
            files.add(result.get());
        }
        return MessageResult.ok(files);
    }
}
```

![](https://hwy-figure-bed.oss-cn-hangzhou.aliyuncs.com/image/20200812144455.png)

我们去看minio服务页面，可以发现文件已经被上传了。

![](https://hwy-figure-bed.oss-cn-hangzhou.aliyuncs.com/image/20200812144501.png)

api操作非常简单，具体源码：https://github.com/HWYWL/spring-boot-2.x-examples/tree/master/spring-boot-docker-minio

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://hwy.ac.cn
- GitHub：https://github.com/HWYWL