package com.yi.xml.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * xml暴露接口
 * @author YI
 * @date 2019-1-28 10:19:50
 */
@Service
public class HelloWorldService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }
}
