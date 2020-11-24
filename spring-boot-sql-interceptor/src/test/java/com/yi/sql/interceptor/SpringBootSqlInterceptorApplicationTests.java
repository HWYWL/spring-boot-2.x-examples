package com.yi.sql.interceptor;

import com.yi.sql.interceptor.dao.BaikeMapper;
import com.yi.sql.interceptor.model.Baike;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSqlInterceptorApplicationTests {
    @Autowired
    BaikeMapper baikeMapper;

    @Test
    public void contextLoads() {
        // 注解
        List<Baike> baikes = baikeMapper.selectListBySQL(5);
        System.out.println(baikes);

        // xml
        List<Baike> book = baikeMapper.selectByBookName("全职法师");
        System.out.println(book);

        // 通用
        List<Baike> selectAll = baikeMapper.selectAll();
        System.out.println(selectAll);

        // 自定义条件
        Example example = new Example(Baike.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "海明威");
        List<Baike> list = baikeMapper.selectByExample(example);
        System.out.println(list);
    }
}
