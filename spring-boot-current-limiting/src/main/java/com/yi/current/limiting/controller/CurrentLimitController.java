package com.yi.current.limiting.controller;

import cn.yueshutong.springbootstartercurrentlimiting.method.annotation.CurrentLimiter;
import com.yi.current.limiting.utils.MessageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流测试类
 *
 * @author YI
 * @date 2019-4-27 12:18:18
 */
@RestController
public class CurrentLimitController {

    /**
     * 每秒最大请求数为2
     * @return
     */
    @GetMapping("/hello")
    @CurrentLimiter(QPS = 2)
    public MessageResult hello(){
        return MessageResult.ok("hello world");
    }
}
