package com.yi.echarts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面显示
 * @author YI
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
