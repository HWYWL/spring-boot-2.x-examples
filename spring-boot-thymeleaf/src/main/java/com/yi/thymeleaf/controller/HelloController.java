package com.yi.thymeleaf.controller;

import com.yi.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用前端模板并返回数据
 *
 * @author YI
 * @date 2018-10-14 20:11:26
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("hello", "Hello, Spring Boot And Thymeleaf!");

        List<User> list = new ArrayList<>();

        User user1 = new User(1, "美女", 18, 12000.50);
        User user2 = new User(2, "校花", 20, 10000.50);
        User user3 = new User(3, "小萝莉", 16, 11000.50);

        list.add(user1);
        list.add(user2);
        list.add(user3);

        map.addAttribute("userList", list);

        return "index";
    }
}
