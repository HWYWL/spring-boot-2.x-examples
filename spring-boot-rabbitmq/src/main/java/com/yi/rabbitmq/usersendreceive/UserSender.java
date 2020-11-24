package com.yi.rabbitmq.usersendreceive;

import com.yi.rabbitmq.common.Constant;
import com.yi.rabbitmq.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送数据 发送者
 * @author YI
 * @date 2018-8-16 15:39:08
 */
@Component
public class UserSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        String context = "RabbitMQ发送者前来报到 " + user.toString();
        System.out.println("Sender================> " + context);
        this.rabbitTemplate.convertAndSend(Constant.USER.getName(), user);
    }
}
