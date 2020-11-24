package com.yi.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YI
 * @date 2020/3/24 11:21
 */
@RestController
public class IndexController {
    //:后面相当于是默认值
    @Value("${test:0}")
    private String test;

    @GetMapping("/apollo")
    public String getConfig() {
        return test;
    }

}