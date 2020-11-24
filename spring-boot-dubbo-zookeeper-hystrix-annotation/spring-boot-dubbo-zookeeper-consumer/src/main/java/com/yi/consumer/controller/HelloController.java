package com.yi.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yi.base.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者
 * @author YI
 * @date 2018-8-20 17:05:30
 */
@RestController
public class HelloController {

    @Reference(
            version = "${hello.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}"
    )
    private HelloService helloService;

    /**
     * 出错时回调sayHelloFallback 方法
     * @param name
     * @return
     */
    @GetMapping("hello/{name}")
    @HystrixCommand(fallbackMethod = "sayHelloFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public String sayHello(@PathVariable String name){
        return helloService.sayHello(name);
    }

    /**
     * 出错时回调sayGoodbyeFallback 方法
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "sayGoodbyeFallback")
    @GetMapping("goodbye/{name}")
    public String sayGoodbye(@PathVariable String name){
        return helloService.sayGoodbye(name);
    }

    public String sayHelloFallback(String name){
        System.out.println("sayHello回调函数");

        return "OK";
    }

    public String sayGoodbyeFallback(String name){
        System.out.println("sayGoodbye回调函数");

        return "OK";
    }
}
