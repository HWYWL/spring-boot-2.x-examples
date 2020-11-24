package com.yi.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动执行
 *
 * @author YI
 * @date 2019-1-28 10:26:07
 */
@Component
public class HelloWorldRunner implements CommandLineRunner {
    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... args) {
        System.out.println(this.helloWorldService.getHelloMessage());
    }
}
