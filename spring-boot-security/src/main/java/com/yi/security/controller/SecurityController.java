package com.yi.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * 页面控制接口
 * @author YI
 * @date 2019-1-26 15:48:28
 */
@Controller
public class SecurityController {
    /**
     * 主页
     * @param model 数据模型
     * @return
     */
    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());

        return "home";
    }

    /**
     * 错误页
     * @return
     */
    @RequestMapping("/foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }
}
