package com.yi.druid;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.json.JSONUtil;
import com.yi.druid.model.Baike;
import com.yi.druid.model.BaikeExample;
import com.yi.druid.service.BaikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisDruidApplicationTests {
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

    /**
     * 更新数据
     */
    @Test
    public void test6() {
        String EncryptStr = "151692df43cd2ca5ed8cb464a6898aa10118b165ee2b87aacd2218d48db38b9373c964cf4ca0c06cea704761627a141f814b7b359509790e32b3de81970e629d1fb41a7646611de9b407316d8d9938ec39a5f7209f65e54269525d7c2841e2ff84a082f2b6dfae28480d5cfc81959d2fd979b969d936670f3cae2f63be2198b862b44b2fccc381dc644db397bbb21afbca3e2bcda4c101d92dc58fe7be5fa65d9b0edb2291dcb0dc79bf7384a7c4b5229f52b5a3b7cfbe8e15d24939c0dc2cbf8be01b935ae56bbfbe87825b0ca91fc2d5067204fb0f9684095deb066be0892555f5554d2b71268d55091f1a6654bc52bdfa178672080ca0719bbb2f2cfefc37e3bf3a428ee5b69f3f62039da6b5a09e19f19ad560c8c96c5de4e8037448cfdd";

        DES des = SecureUtil.des("1234567812345678".getBytes());
        String decryptStr = des.decryptStr(EncryptStr);

        System.out.println(decryptStr);
    }

}
