package com.yi.docker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于界面显示
 * @author YI
 * @date 2018-6-19 14:45:17
 */
@Controller
public class PagesController {

    /**
     * 主页 网关页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
