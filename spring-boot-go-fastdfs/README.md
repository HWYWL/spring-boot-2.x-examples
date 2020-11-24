# spring-boot-go-fastdfs

### 说明
go-fastdfs是一个基于go语言开发的开源文件系统，go-fastdfs（类fastdfs）在运维管理等方面优于fastdfs，更人性化。
执行程序：https://github.com/sjqzhang/go-fastdfs/releases
源码：https://github.com/sjqzhang/go-fastdfs

### 特性
- 支持curl命令上传
- 支持浏览器上传
- 支持HTTP下载
- 支持多机自动同步
- 类fastdfs
- 高性能 （使用leveldb作为kv库）
- 高可靠（设计极其简单，使用成熟组件）
- 无中心设计

### 优点
- 无依赖(单一文件）
- 自动同步
- 失败自动修复
- 按天分目录方便维护
- 支持不同的场景
- 文件自动去重
- 支持目录自定义
- 支持保留原文件名
- 支持自动生成唯一文件名
- 支持浏览器上传
- 支持查看集群文件信息
- 支持集群监控邮件告警
- 支持token下载　token=md5(file_md5+timestamp)
- 运维简单，只有一个角色（不像fastdfs有三个角色Tracker Server,Storage Server,Client），配置自动生成
- 每个节点对等（简化运维）
- 所有节点都可以同时读写

### 启动文件服务器
> linux：./fileserver
> windows：双击fileserver.exe

具体代码实现请看源码，比较简单，但问价那系统也存在一些问题，比如说没有删除的功能这一点就显得优点蛋疼了。

上传结果：
![](https://i.imgur.com/HLYNp1I.png)

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL