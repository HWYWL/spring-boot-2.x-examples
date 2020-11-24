package com.yi.redis.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yi.redis.model.Baike;
import com.yi.redis.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class BaiKeStringController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加百科文档 string结构
     * @return
     */
    @RequestMapping("/addBaikeString")
    public MessageResult addBaikeString() {
        List<Baike> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        list.add(baike1);
        list.add(baike2);

        stringRedisTemplate.opsForValue().set("baike", JSONUtil.toJsonPrettyStr(list));

        return MessageResult.ok();
    }

    /**
     * 根据key查询文档
     * @return
     */
    @RequestMapping("/findBaikeString")
    public MessageResult findBaikeString() {
        String baike = stringRedisTemplate.opsForValue().get("baike");
        JSONArray jsonArray = JSONUtil.parseArray(baike);
        List<Baike> list = jsonArray.toList(Baike.class);

        return MessageResult.ok(list);
    }
}
