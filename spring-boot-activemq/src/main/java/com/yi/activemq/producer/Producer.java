package com.yi.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * 生产者
 *
 * @author YI
 * @date 2019-1-27 21:27:59
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    /**
     * 发送消息
     *
     * @param message 待发送的消息
     */
    public void sendMessage(String message) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
    }

}
