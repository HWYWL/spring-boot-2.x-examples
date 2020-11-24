package com.yi.anima;

import com.yi.anima.model.Baike;
import com.yi.anima.service.IBaiKeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAnimaApplicationTests {
    @Autowired
    IBaiKeService baiKeService;

    @Test
    public void test1() {
        List<Baike> baikes = baiKeService.selectListBySQL();
        System.out.println(baikes);
    }

    @Test
    public void test2() {
        // 查询所有数据
        List<Baike> baikes = baiKeService.selectListAll();
        System.out.println(baikes);
    }

    @Test
    public void test3() {
        Baike baikes = baiKeService.selectById(1);
        System.out.println(baikes);
    }

}

