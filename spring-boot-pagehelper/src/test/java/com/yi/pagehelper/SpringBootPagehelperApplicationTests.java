package com.yi.pagehelper;

import com.github.pagehelper.PageHelper;
import com.yi.pagehelper.dao.BaikeMapper;
import com.yi.pagehelper.model.Baike;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPagehelperApplicationTests {
    @Resource
    BaikeMapper baikeMapper;

    @Test
    public void contextLoads() {
        // 当前页
        int page = 1;
        // 页大小
        int size = 2;

        // 分页
        PageHelper.startPage(page, size);
        List<Baike> baikes = baikeMapper.selectAll();
        System.out.println(baikes);
    }

}

