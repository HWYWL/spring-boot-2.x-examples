package com.yi.lianjia;

import com.yi.lianjia.service.LianjiaInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLianjiaApplicationTests {
    @Autowired
    LianjiaInfoService lianjiaInfoService;

    /**
     * 启动爬虫
     */
    @Test
    void contextLoads() {
        for (int i = 1; i <= 500; i++) {
            lianjiaInfoService.cronTask(i);
        }
    }

}
