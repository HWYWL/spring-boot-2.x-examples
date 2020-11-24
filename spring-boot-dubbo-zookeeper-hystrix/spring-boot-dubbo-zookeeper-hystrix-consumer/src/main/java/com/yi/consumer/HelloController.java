package com.yi.consumer;

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

    /**
     * dubbo消费
     */
    @Reference(
            version = "${hello.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}",
            parameters={
                    "coreSize","10",
                    "maximumSize","20",
                    "keepAliveTimeMinutes","1",
                    "requestVolumeThreshold","20",
                    "sleepWindowInMilliseconds","5000",
                    "errorThresholdPercentage","50",
                    "timeoutInMilliseconds","1000",
                    "sayHello","sayHello",
                    "sayGoodbye","sayGoodbye"
            }
    )
    private HelloService helloService;

    /**
     * localhost:9091/hello/xiaoming
     * @param name
     * @return
     */
    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable String name){
        return helloService.sayHello(name);
    }

    @GetMapping("goodbye/{name}")
    public String sayGoodbye(@PathVariable String name){
        return helloService.sayGoodbye(name);
    }
}
