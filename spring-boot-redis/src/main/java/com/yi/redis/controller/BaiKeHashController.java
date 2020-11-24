package com.yi.redis.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yi.redis.model.Baike;
import com.yi.redis.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class BaiKeHashController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加百科文档 string结构
     * @return
     */
    @RequestMapping("/addBaikeHash")
    public MessageResult addBaikeHash() {
        Map<String, Baike> map = new HashMap<>(16);
        List<String> list1 = new ArrayList<>();

        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        map.put("baike1", baike1);
        map.put("baike2", baike2);

        redisTemplate.opsForHash().putAll("h1", map);

        return MessageResult.ok();
    }

    /**
     * 根据key查询文档
     * @return
     */
    @RequestMapping("/findBaikeHash")
    public MessageResult findBaikeHash() {
        List<String> keys = new ArrayList<>();
        keys.add("baike1");
        keys.add("baike2");

        // 获取单个数据
        Object baike = redisTemplate.opsForHash().get("h1", "baike1");

        // 获取全部数据
        Object baikeMap = redisTemplate.opsForHash().multiGet("h1", keys);

        JSONArray jsonArray = JSONUtil.parseArray(baikeMap);
        List<Baike> list = jsonArray.toList(Baike.class);

        return MessageResult.ok(list);
    }
}
