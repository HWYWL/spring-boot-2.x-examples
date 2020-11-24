package com.yi.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.mybatis.plus.model.Baike;
import com.yi.mybatis.plus.service.IBaiKeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisPlusApplicationTests {
    @Autowired
    IBaiKeService baiKeService;

    @Test
    public void contextLoads() {
        List<Baike> baikes = baiKeService.list();
        System.out.println(baikes);

        IPage<Baike> page = baiKeService.page(new Page<>(0, 1), null);
        System.out.println(page.getRecords());

        List<Baike> list = baiKeService.selectListBySQL();
        System.out.println(list);

        List<Baike> baikeList = baiKeService.selectListByWrapper(new QueryWrapper<Baike>().eq("name", "乱"));
        System.out.println(baikeList);

//        LambdaQueryWrapper<Class<Baike>> lambdaQuery = Wrappers.lambdaQuery(Baike.class);
    }

}
