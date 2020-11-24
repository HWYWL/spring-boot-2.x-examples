package com.yi.druid.controller;

import com.github.hwywl.antnest.annotation.decrypt.DESDecryptBody;
import com.github.hwywl.antnest.annotation.encrypt.DESEncryptBody;
import com.github.hwywl.antnest.annotation.init.GetProperties;
import com.github.hwywl.antnest.annotation.locak.LockKeyParam;
import com.github.hwywl.antnest.annotation.locak.ZooLock;
import com.github.hwywl.antnest.listener.GetPropertiesListener;
import com.yi.druid.model.Baike;
import com.yi.druid.model.BaikeExample;
import com.yi.druid.service.BaikeService;
import com.yi.druid.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 百科接口
 *
 * @author YI
 * @date 2019年6月16日
 */
@RestController
public class BaikeController {
    @Autowired
    BaikeService baikeService;

    /**
     * 通过设置分布式锁，使得方法具有分布式锁的能力
     * <p>分布式锁key：/DISTRIBUTED_LOCK_books</p>
     * @return
     * @throws InterruptedException
     */
    @ZooLock(key = "books")
    @RequestMapping(value = "/selectByExample", method = RequestMethod.POST)
    public MessageResult selectByExample() throws InterruptedException {
        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo("海明威");
        List<Baike> hBaikes = baikeService.selectByExample(example);
        List<Baike> allBooks = new ArrayList<>(hBaikes);

        Thread.sleep(6000);

        return MessageResult.ok(allBooks);
    }

    /**
     * 通过@LockKeyParam 实现分布式锁动态key，如果对象中的id和book分别为 1 和 全职法师
     * <p>分布式锁key：/DISTRIBUTED_LOCK_books/1/全职法师</p>
     * @param baike 前端传入对象参数
     * @return
     * @throws InterruptedException
     */
    @ZooLock(key = "books", timeout = 3000L)
    @RequestMapping(value = "/getBaikes", method = RequestMethod.POST)
    public MessageResult getBaikes(@LockKeyParam(fields = {"id","book"}) Baike baike) throws InterruptedException {
        System.out.println("我进来啦！！！");
        Thread.sleep(5000);
        System.out.println("我出来啦！！！");

        return MessageResult.ok(baike);
    }

    /**
     * 通过@LockKeyParam 实现分布式锁动态key，如果参数name为 全职法师
     * <p>分布式锁key：/DISTRIBUTED_LOCK_books/全职法师</p>
     * @param name 前端书名参数
     * @return
     * @throws InterruptedException
     */
    @ZooLock(key = "books", timeout = 3000L)
    @RequestMapping(value = "/getBaikeName", method = RequestMethod.POST)
    public MessageResult getBaikes(@LockKeyParam String name) throws InterruptedException {
        System.out.println("我进来啦！！！");
        Thread.sleep(5000);
        System.out.println("我出来啦！！！");

        return MessageResult.ok(name);
    }


    @DESEncryptBody
    @RequestMapping(value = "/selectByIdEncrypt", method = RequestMethod.POST)
    @GetProperties(properties = {"d.properties", "c.properties"})
    public MessageResult selectByIdEncrypt(){


        Baike baike = baikeService.selectByPrimaryKey(1L);

        Map cachemap = GetPropertiesListener.CACHEMAP;
        System.out.println(cachemap.get("d.spring.datasource.username"));

        return MessageResult.ok(baike);
    }

    @DESDecryptBody
    @RequestMapping(value = "/selectByIdDecrypt", method = RequestMethod.GET)
    public MessageResult selectByIdDecrypt(@RequestBody String content){

        System.out.println(content);

        return MessageResult.ok(content);
    }


    @RequestMapping(value = "/getBaike", method = RequestMethod.POST)
    public MessageResult getBaike(String name){

        return MessageResult.ok(name);
    }
}
