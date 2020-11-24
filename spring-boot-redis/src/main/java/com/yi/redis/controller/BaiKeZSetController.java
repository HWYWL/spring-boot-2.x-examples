package com.yi.redis.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yi.redis.model.Baike;
import com.yi.redis.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
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
public class BaiKeZSetController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加百科文档 string结构
     * @return
     */
    @RequestMapping("/addBaikeZSet")
    public MessageResult addBaikeZSet() {
        List<String> list1 = new ArrayList<>();

        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        List<String> list3 = new ArrayList<>();
        list3.add("玄幻");
        list3.add("小说");
        Baike baike3 = new Baike(2, "斗罗大陆", list3, 7000, 8, "乱", "唐家三少", 300, 0, new Date(), new Date());

        redisTemplate.opsForZSet().add("baikeZSet", baike1, baike1.getGood());
        redisTemplate.opsForZSet().add("baikeZSet", baike2, baike2.getGood());
        redisTemplate.opsForZSet().add("baikeZSet", baike3, baike3.getGood());

        return MessageResult.ok();
    }

    /**
     * 根据key查询文档
     * @return
     */
    @RequestMapping("/findBaikeZSet")
    public MessageResult findBaikeZSet() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        // 从小到大排序,获得全部数据
//        Set range = zSetOperations.range("baikeZSet", 0, -1);

        // 从大到小排序,获得两条数据
//        Set range = zSetOperations.reverseRange("baikeZSet", 0, 1);

        // 从大到小排序,获得全部数据
        Set range = zSetOperations.reverseRange("baikeZSet", 0, -1);

        JSONArray jsonArray = JSONUtil.parseArray(range);
        List<Baike> list = jsonArray.toList(Baike.class);

        return MessageResult.ok(list);
    }
}
