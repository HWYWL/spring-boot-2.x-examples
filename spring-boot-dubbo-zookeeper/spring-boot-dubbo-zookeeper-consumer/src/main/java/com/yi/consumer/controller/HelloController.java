package com.yi.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable String name){
        return helloService.sayHello(name);
    }

    @GetMapping("goodbye/{name}")
    public String sayGoodbye(@PathVariable String name){
        return helloService.sayGoodbye(name);
    }
}
