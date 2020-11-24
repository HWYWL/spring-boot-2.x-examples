package com.yi.gitee.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 *
 * @author YI
 * @date 2019-3-28 10:59:45
 */
@Controller
public class PageController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
