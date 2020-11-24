package com.yi.async.controller;

import cn.hutool.json.JSONUtil;
import com.yi.async.model.Baike;
import com.yi.async.service.BaikeService;
import com.yi.async.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 百科接口调用
 *
 * @author YI
 * @date 2019-3-20 16:42:07
 */
@RestController
public class BaikeController {
    @Autowired
    BaikeService baikeService;

    /**
     * 异步调用，保存数据
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public MessageResult save(){
        List<String> list1 = new ArrayList<>();
        list1.add("魔幻");
        list1.add("小说");
        String tag1 = JSONUtil.toJsonStr(list1);
        Baike baike1 = new Baike("遮天", tag1, 661000, 11, "辰东", "男", 100, Byte.valueOf("1"));

        List<String> list2 = new ArrayList<>();
        list2.add("文学");
        list2.add("小说");
        String tag2 = JSONUtil.toJsonStr(list2);
        Baike baike2 = new Baike("红楼梦", tag2, 900880, 22, "曹雪芹", "女", 1000, Byte.valueOf("1"));

        Future<String> future1 = baikeService.save(baike1);
        Future<String> future2 = baikeService.save(baike2);

        List<Future<String>> list = new ArrayList<>();
        list.add(future1);
        list.add(future2);

        return MessageResult.ok(list);
    }
}
