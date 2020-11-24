package com.yi.psql;

import com.alibaba.fastjson.JSONObject;
import com.yi.psql.mapper.BaikeDAO;
import com.yi.psql.model.Baike;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBootPsqlApplicationTests {
    @Autowired
    BaikeDAO baikeDAO;

    @Test
    void insertSelective() {
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        String tag1 = JSONObject.toJSONString(list1);
        Baike baike1 = new Baike("老人与海", tag1, 1000, 10, "海明威", "男", 100);

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        String tag2 = JSONObject.toJSONString(list2);
        Baike baike2 = new Baike("全职法师", tag2, 1000000, 5, "乱", "男", 1000);

        baikeDAO.insertSelective(baike1);
        baikeDAO.insertSelective(baike2);
    }

    @Test
    void updateByPrimaryKeySelective() {
        Baike baike = baikeDAO.selectByPrimaryKey(3);
        baike.setGood(500);
        baike.setUpdateDate(new Date());
        baikeDAO.updateByPrimaryKeySelective(baike);
    }

    @Test
    void selectByPrimaryKey() {
        Baike baike = baikeDAO.selectByPrimaryKey(3);
        System.out.println(baike);
    }

    @Test
    void deleteByPrimaryKey() {
        int delete = baikeDAO.deleteByPrimaryKey(3);
        System.out.println(delete);
    }

}
