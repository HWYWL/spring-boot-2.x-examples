package com.example.webflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面
 * @author YI
 * @date 2018-8-15 11:32:13
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
