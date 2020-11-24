package com.yi.mongodb.controller;

import com.yi.mongodb.model.Baike;
import com.yi.mongodb.service.MongoDbBaiKeService;
import com.yi.mongodb.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mongoDB执行操作
 * @author YI
 * @date 2018-8-22 15:14:14
 */
@RestController
@RequestMapping("/mongodb")
public class BaiKeController {
    @Autowired
    private MongoDbBaiKeService mongoDbBaiKeService;

    /**
     * 根据名称查询文档
     * @param id 文档主键
     * @return
     */
    @RequestMapping("/baike")
    public MessageResult findUser(Integer id) {
        Baike baike = mongoDbBaiKeService.findById(id);

        return MessageResult.ok(baike);
    }

    /**
     * 添加百科文档
     * @return
     */
    @RequestMapping("/addbaike")
    public MessageResult addDict() {
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 5, "乱", "男", 1000, 0, new Date(), new Date());

        mongoDbBaiKeService.addDict(baike1);
        mongoDbBaiKeService.addDict(baike2);

        return MessageResult.ok();
    }

    /**
     * 查询comment的bad属性大于等于一定数量的Baike对象
     * @param bad
     * @return
     */
    @RequestMapping("/querybad")
    public MessageResult queryBad(int bad) {
        List<Baike> baikes = mongoDbBaiKeService.queryBad(bad);

        return MessageResult.ok(baikes);
    }

    /**
     * 点赞,这个标签名的点个赞
     * http://127.0.0.1:8080/mongodb/baike/tag?tag=魔幻
     * @param tag   标签名称
     * @return
     */
    @RequestMapping("/baike/tag")
    public MessageResult addOne(String tag) {
        String one = mongoDbBaiKeService.addOne(tag);

        return MessageResult.ok(one);
    }

    /**
     * 检索置顶标签，分页显示
     * @param tag       标签名称
     * @param pageNum
     * @return
     */
    @RequestMapping("/baike/tagPageNum")
    public MessageResult findBaike(String tag, int pageNum) {
        List<Baike> baikes = mongoDbBaiKeService.findBaike(tag, pageNum);

        return MessageResult.ok(baikes);
    }

    /**
     *  查询点赞数小于good，负面评价大于bad的所有Baike
     * @param tag   标签名称
     * @param good  ＜(＾－＾)＞ 你最棒 点赞数
     * @param bad   (｡•ˇ‸ˇ•｡)滚犊子    负面数
     * @return
     */
    @RequestMapping("/baike/tagRange")
    public MessageResult findBaike(String tag, int good, int bad) {
        List<Baike> baikes = mongoDbBaiKeService.findBaike(tag, good, bad);

        return MessageResult.ok(baikes);
    }

    /**
     * 更新文档
     * @param baike
     * @return
     */
    @RequestMapping("/updatebaike")
    public MessageResult updateDict(Baike baike) {
        Baike bk = mongoDbBaiKeService.updateDict(baike);

        return MessageResult.ok(bk);
    }

    /**
     * 通过id删除文档
     * @param id
     * @return
     */
    @RequestMapping("/deletebaike")
    public MessageResult deleteDict(Integer id) {
        Baike baike = mongoDbBaiKeService.deleteDict(id);

        return MessageResult.ok(baike);
    }
}
