package com.yi.storm.task;

import com.yi.storm.domain.PaymentInfo;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 模拟生成支付信息
 *
 * @author huangwenyi
 * @date 2019-9-2
 */
@Component
@EnableScheduling
public class PayMentInfoProducerTask {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 一秒钟生成一条数据
     */
    @Scheduled(cron = "0/1 * * * * ? ")
    public void getGameConfig() {
        ProducerRecord<String, String> order = new ProducerRecord<>("order", new PaymentInfo().random());
        kafkaTemplate.send(order);

        System.out.println("商品数据：" + order.value());
    }
}
