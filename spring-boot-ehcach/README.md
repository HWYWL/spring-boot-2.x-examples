# spring-boot-ehcach

EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点

我们通过xml文件配置缓存的使用方式
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <diskStore path="java.io.tmpdir/Tmp_EhCache"/>
    <defaultCache
            eternal="false"
            maxElementsInMemory="1000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="0"
            timeToLiveSeconds="100"
            memoryStoreEvictionPolicy="LRU"/>

    <cache
            name="book_cache"
            eternal="false"
            maxElementsInMemory="100"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="0"
            timeToLiveSeconds="30"
            memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```

随后在我们application.properties文件中配置加载路径
```
spring.cache.ehcache.config=classpath:config/ehcache.xml

#缓存配置
spring.cache.cache-names=c1,c2
```

请看代码具体操作，在test中有测试类

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL