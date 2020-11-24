package com.yi.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yi.base.HelloService;

/**
 * 服务降级
 *
 * @author YI
 * @date 2019-6-11
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
        return "sayHello " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        int i = 1 / 0;
        return "sayGoodbye " + name;
    }
}
