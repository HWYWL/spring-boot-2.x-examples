# spring-boot-dubbo-zookeeper-hystrix
引入**spring-boot-dubbo-zookeeper-hystrix-base**后会通过Dubbo的Activate机制自动激活HystrixFilter,对dubbo:reference进行资源隔离和熔断保护。

# 线程池隔离
默认按dubbo:reference进行线程池资源隔离，可以认为一个dubbo:reference为一个领域服务，reference里的所有方法在一个线程池资源里运行，多个reference之间线程池资源是隔离的，这样可以根据具体的业务场景对不同的reference设置不同的线程池资源，并且当某个reference出现阻塞故障时不会导致容器线程资源被耗尽，从而影响其他服务。

默认线程池配置如下：
```
<dubbo:parameter key="coreSize" value="10"/>
<dubbo:parameter key="maximumSize" value="20"/>
<dubbo:parameter key="keepAliveTimeMinutes" value="1"/>
```
| 参数        | 说明           | 默认值  |
| ------------- |:-------------:| -----:|
| coreSize      | 核心线程数大小 | 10 |
| maximumSize      | 空闲线程持有时间（分钟）      |   20 |
| keepAliveTimeMinutes | 最大线程数大小      |    1 |

# 信号量隔离
将自定义属性 isolation 为设置 SEMAPHORE 进行信号量隔离

` <dubbo:parameter key="isolation" value="SEMAPHORE"/>` 

isolation属性：

| 属性值        | 说明          | 备注 |
| ------------- |:-------------:| :-----|
| SEMAPHORE    | 信号量隔离 | 适用于本地方法调用，或者QPS非常高的调用 |
| THREAD      | 线程池隔离 -默认值      |  |

### 最大并发请求数量
设置属性 maxConcurrentRequests 默认值为 10

`<dubbo:parameter key="maxConcurrentRequests" value="10"/>`


# 熔断保护
每个dubbo:reference的服务方法会封装成command,提供熔断保护和优雅降级功能。

默认熔断配置如下：
```
<dubbo:parameter key="requestVolumeThreshold" value="20"/>
<dubbo:parameter key="sleepWindowInMilliseconds" value="5000"/>
<dubbo:parameter key="errorThresholdPercentage" value="50"/>
<dubbo:parameter key="timeoutInMilliseconds" value="1000"/>
```
| 参数        | 说明           | 默认值  |   备注      |
| ------------- |:-------------| :---- |:---- |
| requestVolumeThreshold      | 熔断判断请求数阈值 | 20 |一个统计周期内（默认10秒）请求不少于requestVolumeThreshold才会进行熔断判断 |
| sleepWindowInMilliseconds     | 熔断触发错误率阈值      |   5000 | 超过50%错误触发熔断|
| errorThresholdPercentage | 熔断触发后多久恢复half-open状态     |    50 |熔断后sleepWindowInMilliseconds毫秒会放入一个请求，如果请求处理成功，熔断器关闭，否则熔断器打开，继续等待sleepWindowInMilliseconds |
| timeoutInMilliseconds | 任务执行超时时间       |    1000 | 注意该时间和dubbo自己的超时时间不要冲突，以这个时间优先，比如consumer设置3秒，那么当执行时hystrix会提前超时 |

# 服务降级
降级触发条件如下：
1. 远程服务调用超时
1. 远程服务内部执行返回异常结果（如内部抛出运行时异常）
1. Hystrix will execute this fallback for all types of failure such as run() failure, timeout, thread pool or semaphore rejection, and circuit-breaker short-circuiting

服务降级方法通过SPI扩展的方式加载、运行，实现方式如下：
### 1、继承实现Fallback接口，提供降级实现，如下：
```
package com.yi.fallback;

import com.netease.hystrix.dubbo.rpc.filter.Fallback;

/**
 * 回调函数
 * @author YI
 * @date 2018-9-5 21:16:31
 */
public class DemoFallback implements Fallback {

    @Override
    public Object invoke() {
        System.out.println("sayHello 方法回调。。。");
        return null;
    }
}
```
### 2、按dubbo SPI扩展规则进行配置
1. resources目录增加文件/META-INF/dubbo/com.netease.hystrix.dubbo.rpc.filter.Fallback（此文件名必须是Fallback类的包名，不然会找不到）
2. demoFallback=com.netease.urs.dubbo.rpc.DemoFallback

### 3、dubbo:reference增加method fallback配置
```
<dubbo:method name="demo">
    <dubbo:parameter key="fallback" value="demoFallback"/>
</dubbo:method>
```
## Fallback并发调用量控制
设置属性 fallbackMaxConcurrentRequests 默认值为 50

`<dubbo:parameter key="fallbackMaxConcurrentRequests" value="50"/>`

# 画重点,敲黑板
在我的源码中我删除了xml配置文件,dubbo全部是使用注解和配置完成,让我们更简单的完成操作,我们来看看吧:

生产者暴露服务

![](https://i.imgur.com/L9Emelb.jpg)

消费者使用

![](https://i.imgur.com/iA2xVxq.jpg)

注解 parameters 中对应的参数就是上文提到的**熔断保护**的参数，好了，快乐的写代码吧！

### 感谢
基于此大佬的源码修改而来: [dubbo-hystrix-support](https://github.com/yskgood/dubbo-hystrix-support)
移植了源码,增加了部分例程,例如回调.

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL