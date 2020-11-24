package com.yi.redis.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yi.redis.model.Baike;
import com.yi.redis.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Redis执行操作
 * @author YI
 * @date 2018-8-22 15:14:14
 */
@RestController
@RequestMapping("/redis")
public class BaiKeSetController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加百科文档 string结构
     * @return
     */
    @RequestMapping("/addBaikeSet")
    public MessageResult addBaikeSet() {
        List<String> list1 = new ArrayList<>();

        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        Long b1 = redisTemplate.opsForSet().add("baike", baike1);
        Long b2 = redisTemplate.opsForSet().add("baike", baike2);

        return MessageResult.ok();
    }

    /**
     * 根据key查询文档
     * @return
     */
    @RequestMapping("/findBaikeSet")
    public MessageResult findBaikeSet() {
        SetOperations setOperations = redisTemplate.opsForSet();

        Set baike = setOperations.members("baike");

        JSONArray jsonArray = JSONUtil.parseArray(baike);
        List<Baike> list = jsonArray.toList(Baike.class);

        return MessageResult.ok(list);
    }

    /**
     * 根据key删除文档
     * @return
     */
    @RequestMapping("/delBaikeSet")
    public MessageResult delBaikeSet() {
        Boolean delete = redisTemplate.delete("baike");

        return MessageResult.ok(delete);
    }
}
