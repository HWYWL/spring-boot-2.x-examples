package com.yi.redshift.controller;

import com.alibaba.fastjson.JSONObject;
import com.yi.redshift.mapper.BaikeMapper;
import com.yi.redshift.model.Baike;
import com.yi.redshift.service.IBaiKeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
public class BaikeController {
    @Autowired
    BaikeMapper baikeMapper;

    @Autowired
    IBaiKeService iBaiKeService;

    @PostMapping("/add")
    public void add(int page) {
        long start = System.currentTimeMillis();
        long start1 = System.currentTimeMillis();
        List<Baike> list = new ArrayList<>();

        for (int i = 1; i < 100000; i++) {
            List<String> list2 = new ArrayList<>();
            list2.add("魔幻");
            list2.add("小说");
            String label2 = JSONObject.toJSONString(list2);
            Random random = new Random();
            Baike baike2 = new Baike(i, "全职法师", label2, random.nextInt(1000), 5, "乱", "男", 1000);

            list.add(baike2);

            if (list.size() > page) {
                    baikeMapper.insertBaike(list);
                    long end = System.currentTimeMillis();
                    log.info("批量数据：" + page + " 耗时：" + ((start - end)) + "ms");
                    start = System.currentTimeMillis();
                list.clear();
            }

        }

        long end = System.currentTimeMillis();
        log.info("耗时：" + ((start1 - end) / 1000) + "s");
    }

    @PostMapping("/addPage")
    public void addPage(int page) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        long start1 = System.currentTimeMillis();

        for (int i = 1; i < 100; i++) {
            List<Baike> list = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                AtomicLong start = new AtomicLong(System.currentTimeMillis());
                List<String> list2 = new ArrayList<>();
                list2.add("魔幻");
                list2.add("小说");
                String label2 = JSONObject.toJSONString(list2);
                Random random = new Random();
                Baike baike2 = new Baike(i, "全职法师", label2, random.nextInt(1000), 5, "乱", "男", 1000);

                list.add(baike2);

                if (list.size() >= page) {

                    fixedThreadPool.execute(() -> {
                        baikeMapper.insertBaike(list);
                        long end = System.currentTimeMillis();
                        log.info("批量数据：" + page + " 耗时：" + ((start.get() - end)) + "ms");
                        start.set(System.currentTimeMillis());
                        list.clear();
                    });
                }
            }
        }

        long end = System.currentTimeMillis();
        log.info("耗时：" + ((start1 - end) / 1000) + "s");
    }

    @PostMapping("/addPageBatch")
    public void addPageBatch(int page) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        long start1 = System.currentTimeMillis();

        for (int i = 1; i < 100; i++) {
            List<Baike> list = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                AtomicLong start = new AtomicLong(System.currentTimeMillis());
                List<String> list2 = new ArrayList<>();
                list2.add("魔幻");
                list2.add("小说");
                String label2 = JSONObject.toJSONString(list2);
                Random random = new Random();
                Baike baike2 = new Baike(i, "全职法师", label2, random.nextInt(1000), 5, "乱", "男", 1000);

                list.add(baike2);

                if (list.size() >= page) {
                    fixedThreadPool.execute(() -> {
                        iBaiKeService.saveBatch(list);
                        long end = System.currentTimeMillis();
                        log.info("批量数据：" + page + " 耗时：" + ((start.get() - end)) + "ms");
                        start.set(System.currentTimeMillis());
                        list.clear();
                    });
                }
            }
        }

        long end = System.currentTimeMillis();
        log.info("耗时：" + ((start1 - end) / 1000) + "s");
    }
}
