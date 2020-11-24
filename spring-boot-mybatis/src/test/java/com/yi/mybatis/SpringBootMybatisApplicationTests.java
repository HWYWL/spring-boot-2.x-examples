package com.yi.mybatis;

import cn.hutool.json.JSONUtil;
import com.yi.mybatis.model.Baike;
import com.yi.mybatis.model.BaikeExample;
import com.yi.mybatis.service.BaikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
    @Autowired
    BaikeService baikeService;

    /**
     * 添加数据
     */
    @Test
    public void test1() {
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        String tag1 = JSONUtil.toJsonStr(list1);
        Baike baike1 = new Baike("老人与海", tag1, 1000, 10, "海明威", "男", 100);

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        String tag2 = JSONUtil.toJsonStr(list2);
        Baike baike2 = new Baike("全职法师", tag2, 1000000, 5, "乱", "男", 1000);

        baikeService.insertSelective(baike1);
        baikeService.insertSelective(baike2);
    }

    /**
     * 查询数据
     */
    @Test
    public void test2() {
        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo("海明威");

        List<Baike> baikes = baikeService.selectByExample(example);

        System.out.println(baikes);
    }

    /**
     * 查询数据
     */
    @Test
    public void test3() {
        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        // 查询点赞数大于1000的数据
        criteria.andGoodGreaterThan(1000);

        List<Baike> baikes = baikeService.selectByExample(example);

        System.out.println(baikes);
    }

    /**
     * 更新数据
     */
    @Test
    public void test4() {
        Baike baike = new Baike();
        baike.setId(1L);
        baike.setGood(1001);

        // 不更新为null的字段
        int i = baikeService.updateByPrimaryKeySelective(baike);

        System.out.println("更新 " + i + " 条数据");
    }

    /**
     * 更新数据
     */
    @Test
    public void test5() {
        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        criteria.andBadBetween(5, 10);

        // 按条件删除（删除鄙视数5到10的数据）
        int i = baikeService.deleteByExample(example);

        System.out.println("更新 " + i + " 条数据");
    }

}
