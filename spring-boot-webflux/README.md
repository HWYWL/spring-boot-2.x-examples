# spring-boot-webflux

### 说明
响应式编程是基于异步和事件驱动的非阻塞程序，只需要在程序员内启动少量线程扩展，而不是水平通过集群扩展。
用大白话讲，我们以前编写的大部分都是阻塞类的程序，当一个请求过来时任务会被阻塞，直到这个任务完成后再返回给前端；响应式编程接到请求后只是提交了一个请求给后端，后端会再安排另外的线程去执行任务，当任务执行完成后再异步通知到前端。

![](https://i.imgur.com/hZwpevU.png)

如图所示，WebFlux 模块从上到下依次是 Router Functions、WebFlux、Reactive Streams 三个新组件。

- Router Functions
对标准的 @Controller，@RequestMapping 等的 Spring MVC 注解，提供一套 函数式风格的 API，用于创建 Router、Handler 和Filter。

- WebFlux
核心组件，协调上下游各个组件提供 响应式编程 支持。

- Reactive Streams
一种支持 背压 (Backpressure) 的 异步数据流处理标准，主流实现有 RxJava 和 Reactor，Spring WebFlux 集成的是 Reactor。

### Spring Webflux

Spring Boot 2.0 包括一个新的 spring-webflux 模块。该模块包含对响应式 HTTP 和 WebSocket 客户端的支持，以及对 REST，HTML 和 WebSocket 交互等程序的支持。一般来说，Spring MVC 用于同步处理，Spring Webflux 用于异步处理。

Spring Boot Webflux 有两种编程模型实现，一种类似 Spring MVC 注解方式，另一种是基于 Reactor 的响应式方式。

这个代码是很简单的hello，更多内容请看其他例程。

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：http://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL