package com.yi.kafka.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka消息消费者
 * @author YI
 * @data 2018-8-11 11:24:58
 */
@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"topic-1","topic-2"})
    public void processMessage(String content) {

        System.out.println("消息被消费"+content);
    }
}
