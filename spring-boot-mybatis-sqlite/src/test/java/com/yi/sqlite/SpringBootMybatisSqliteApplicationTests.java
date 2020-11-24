package com.yi.sqlite;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.yi.sqlite.model.Baike;
import com.yi.sqlite.model.BaikeExample;
import com.yi.sqlite.service.BaikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisSqliteApplicationTests {
    @Autowired
    BaikeService baikeService;

    /**
     * 添加数据
     */
    @Test
    public void test1() {
        String now = DateUtil.now();

        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        String tag1 = JSONUtil.toJsonStr(list1);
        Baike baike1 = new Baike(1,"老人与海", tag1, 1000, 10, "海明威", "男", 100, 1, now, now);

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        String tag2 = JSONUtil.toJsonStr(list2);
        Baike baike2 = new Baike(2,"全职法师", tag2, 1000000, 5, "乱", "男", 1000, 1, now, now);

        baikeService.saveSelective(baike1);
        baikeService.saveSelective(baike2);
    }

    @Test
    public void test2() {
        Baike baike = baikeService.selectByPrimaryKey(1);
        System.out.println(baike);
    }

    @Test
    public void test3() {
        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        criteria.andBookEqualTo("全职法师");

        List<Baike> baikes = baikeService.selectByExample(example);
        baikes.forEach(System.out::println);
    }
}
