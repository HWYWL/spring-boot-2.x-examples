package com.yi.douban;

import cn.hutool.core.io.FileUtil;
import com.yi.douban.model.Nezha;
import com.yi.douban.service.FilmReviewInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBootDoubanApplicationTests {
    @Autowired
    FilmReviewInfoService reviewInfoService;

    @Test
    void contextLoads() {
        // 目前有12370天影评，每页20条，共618页
        for (int i = 1; i <= 618; i++) {
            reviewInfoService.cronTask(20 * i);
        }
    }

    @Test
    void test01() {
        List<Nezha> nezhas = reviewInfoService.list();
        List<String> collect = nezhas.stream().map(e -> e.getFilmReview()).collect(Collectors.toList());
        FileUtil.appendUtf8Lines(collect, "D:/Users/Desktop/fsdownload/nezha.txt");
    }
}
