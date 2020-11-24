package com.yi.kafka.controller;

import com.yi.kafka.message.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/test")
    public void test() {
        kafkaProducer.sendMessage("topic-1", "topic--------1");
        kafkaProducer.sendMessage("topic-2", "topic--------2");
    }
}
