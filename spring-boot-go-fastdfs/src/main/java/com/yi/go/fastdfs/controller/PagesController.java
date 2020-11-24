package com.yi.go.fastdfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于界面显示
 * @author YI
 * @date 2019-1-29 10:57:20
 */
@Controller
public class PagesController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
