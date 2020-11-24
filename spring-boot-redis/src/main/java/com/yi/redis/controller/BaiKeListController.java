package com.yi.redis.controller;

import com.yi.redis.model.Baike;
import com.yi.redis.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Redis执行操作
 * @author YI
 * @date 2018-8-22 15:14:14
 */
@RestController
@RequestMapping("/redis")
public class BaiKeListController {
    @Autowired
    private RedisTemplate<String, Baike> redisTemplate;

    /**
     * 添加百科文档 LIST结构
     * @return
     */
    @RequestMapping("/addBaikeList")
    public MessageResult addBaikeList() {
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        BoundListOperations<String, Baike> baikeList = redisTemplate.boundListOps("baikeList");

        baikeList.leftPushAll(baike1, baike2);

        // 10秒过期
//        redisTemplate.opsForValue().set("baike",baike1,10, TimeUnit.SECONDS);

        return MessageResult.ok();
    }

    /**
     * 根据key查询文档
     * @return
     */
    @RequestMapping("/findBaikeList")
    public MessageResult findBaikeList() {
        BoundListOperations<String, Baike> listOperations = redisTemplate.boundListOps("baikeList");
        List<Baike> list = listOperations.range(0, 10);

        return MessageResult.ok(list);
    }
}
