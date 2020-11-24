package com.yi.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yi.base.HelloService;

import java.util.concurrent.TimeUnit;

/**
 * 生产者提供服务
 *
 * @author YI
 * @date 2018-8-20 17:04:57
 */
@Service(
        version = "${hello.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {

        }
        return "Hello " + name + " !";
    }

    @Override
    public String sayGoodbye(String name) {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {

        }
        return "Goodbye " + name + " !";
    }
}
