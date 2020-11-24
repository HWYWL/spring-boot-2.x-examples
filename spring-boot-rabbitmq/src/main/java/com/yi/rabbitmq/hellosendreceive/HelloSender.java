package com.yi.rabbitmq.hellosendreceive;

import cn.hutool.core.date.DateUtil;
import com.yi.rabbitmq.common.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送数据 发送者
 * @author YI
 * @date 2018-8-16 15:39:08
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "RabbitMQ发送者前来报到 " + DateUtil.now();
        System.out.println("Sender================> " + context);
        this.rabbitTemplate.convertAndSend(Constant.HELLO.getName(), context);
    }
}
