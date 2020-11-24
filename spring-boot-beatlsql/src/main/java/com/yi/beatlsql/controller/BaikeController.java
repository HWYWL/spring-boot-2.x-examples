package com.yi.beatlsql.controller;

import cn.hutool.json.JSONUtil;
import com.yi.beatlsql.model.Baike;
import com.yi.beatlsql.service.BaikeService;
import com.yi.beatlsql.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据返回
 * @author YI
 * @date 2019-3-5 22:16:35
 */
@RestController
public class BaikeController {
    @Autowired
    public BaikeService baikeService;

    /**
     * 查询数据
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public MessageResult home() {
        Baike query = new Baike();

        query.setName("乱");
        List<Baike> baikes = baikeService.allBaike(query);

        return MessageResult.ok(baikes);
    }

    /**
     * 保存数据
     * @return
     */
    @RequestMapping(value = "/addBaike", method = RequestMethod.POST)
    public MessageResult addBaike() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("文学");
        list1.add("小说");
        String tag1 = JSONUtil.toJsonStr(list1);
        Baike baike1 = new Baike("老人与海", tag1, 1000, 10, "海明威", "男", 100);

        list2.add("魔幻");
        list2.add("小说");
        String tag2 = JSONUtil.toJsonStr(list2);
        Baike baike2 = new Baike("全职法师", tag2, 1000000, 5, "乱", "男", 1000);

        baikeService.save(baike1);
        baikeService.save(baike2);

        return MessageResult.ok(baikeService.total());
    }
}
