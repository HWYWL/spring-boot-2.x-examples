package com.yi.jpa.controller;

import com.yi.jpa.model.Baike;
import com.yi.jpa.repository.BaikeRepository;
import com.yi.jpa.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 百科对象操作
 * @author YI
 * @date 2018-8-27 10:29:14
 */
@RestController
@RequestMapping("/baike")
public class BaikeController {
    @Autowired
    BaikeRepository baikeRepository;

    /**
     * 根据名字模糊查找
     * http://127.0.0.1:8080/baike/search?name=海
     * @param name 包含作者姓名的关键字
     * @return
     */
    @RequestMapping("/search")
    public MessageResult search(String name) {
        List<Baike> result = this.baikeRepository.findByNameContaining(name);

        return MessageResult.ok(result);
    }

    /**
     * 根据用户名字精确查找
     * http://127.0.0.1:8080/baike/searchPinpoint?name=海明威
     * @param name 包含作者姓名
     * @return
     */
    @RequestMapping("/searchPinpoint")
    public MessageResult searchPinpoint(String name) {
        List<Baike> result = this.baikeRepository.getByNameIs(name);

        return MessageResult.ok(result);
    }

    /**
     * 根据用户名字和书查找
     * http://127.0.0.1:8080/baike/searchNameBook?name=乱&book=全职法师
     * @param name 包含作者姓名的关键字
     * @return
     */
    @RequestMapping("/searchNameBook")
    public MessageResult searchNameBook(String name, String book) {
        List<Baike> result = this.baikeRepository.getByNameIsAndBookIs(name, book);

        return MessageResult.ok(result);
    }
}
