package com.yi.jib.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YI
 * @date 2020/5/8 11:32
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello JIB";
    }
}