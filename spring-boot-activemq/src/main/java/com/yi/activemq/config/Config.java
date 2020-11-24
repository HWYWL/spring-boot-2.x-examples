package com.yi.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * MQ配置
 *
 * @author YI
 * @date 2019-1-27 21:26:52
 */
@Configuration
@EnableJms
public class Config {
    public static final String QUEUE = "sample.queue";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE);
    }
}
