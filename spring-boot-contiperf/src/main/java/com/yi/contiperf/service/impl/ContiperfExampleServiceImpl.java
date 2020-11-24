package com.yi.contiperf.service.impl;

import com.yi.contiperf.service.ContiperfExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试接口实现
 *
 * @author YI
 * @date 2019-3-7 21:54:20
 */
@Slf4j
@Service
public class ContiperfExampleServiceImpl implements ContiperfExampleService {

    private final Random RANDOM = new Random();

    @Override
    public List<String> findAll() {
        try {
            int sleepSecond = RANDOM.nextInt(10);
            log.info("#findAll(): sleep {} seconds..", sleepSecond);
            Thread.sleep(sleepSecond * 1000);
        } catch (InterruptedException e) {
            // ignore
        }

        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            resultList.add("string_" + i);
        }

        return resultList;
    }
}
