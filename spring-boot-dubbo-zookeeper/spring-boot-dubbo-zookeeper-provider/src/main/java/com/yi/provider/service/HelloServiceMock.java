package com.yi.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yi.base.HelloService;

/**
 * 服务降级
 *
 * @author YI
 * @date 2019-6-11
 */
@Service(mock = "true")
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello(String name) {
        return "sayHello 方法异常";
    }

    @Override
    public String sayGoodbye(String name) {
        return "sayGoodbye 方法异常";
    }
}
