package com.yi.beetl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 模板引擎测试接口
 * @author YI
 * @date 2019-3-1 09:36:50
 */
@Controller
public class BeetlController {

    @GetMapping({"/","/index","/beetl"})
    public String beetl(Model model){
        model.addAttribute("beetl","你好，Beetl模板引擎");

        return "index.html";
    }
}
