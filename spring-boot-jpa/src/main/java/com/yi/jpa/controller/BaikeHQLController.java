package com.yi.jpa.controller;

import com.yi.jpa.model.Baike;
import com.yi.jpa.repository.BaikeHQLRepository;
import com.yi.jpa.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 百科对象通过HQL操作
 * @author YI
 * @date 2018-8-27 10:29:14
 */
@RestController
@RequestMapping("/baike/hql")
public class BaikeHQLController {
    @Autowired
    BaikeHQLRepository baikeHQLRepository;

    /**
     * 根据名字查找
     * http://127.0.0.1:8080/baike/hql/search?name=海明威
     * @param name 用户名称
     * @return
     */
    @RequestMapping("/search")
    public MessageResult search(String name) {
        List<Baike> result = this.baikeHQLRepository.searchBaike(name);

        return MessageResult.ok(result);
    }

    /**
     * 根据用户名字精确查找并按照id排序
     * http://127.0.0.1:8080/baike/hql/searchSort?name=海明威
     * @param name 用户名称
     * @return
     */
    @RequestMapping("/searchSort")
    public MessageResult searchSort(String name) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        List<Baike> result = this.baikeHQLRepository.searchBaike(name, sort);

        return MessageResult.ok(result);
    }

    /**
     * 根据用户名字和书查找
     * http://127.0.0.1:8080/baike/hql/setNameContent?goldcoin=10000000&name=乱
     * @param goldcoin 金币数量
     * @param name 用户名称
     * @return
     */
    @RequestMapping("/setNameContent")
    public MessageResult setNameContent(Integer goldcoin, String name) {
        int content = this.baikeHQLRepository.setNameContent(goldcoin, name);

        return MessageResult.ok(content);
    }
}
