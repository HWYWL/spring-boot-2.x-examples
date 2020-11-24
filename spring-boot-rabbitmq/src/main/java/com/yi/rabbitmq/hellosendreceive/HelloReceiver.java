package com.yi.rabbitmq.hellosendreceive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收数据 接收者
 * 发送者和接收者的queue name必须一致，不然不能接收
 * @author YI
 * @date 2018-8-16 15:44:05
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver==============> RabbitMQ接受者前来报到 " + hello);
    }
}
