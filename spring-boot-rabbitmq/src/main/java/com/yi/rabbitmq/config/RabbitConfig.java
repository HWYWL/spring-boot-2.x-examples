package com.yi.rabbitmq.config;

import com.yi.rabbitmq.common.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列配置
 * @author YI
 * @date 2018-8-16 15:35:42
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue(Constant.HELLO.getName());
    }

    @Bean
    public Queue neoQueue() {
        return new Queue(Constant.NEO.getName());
    }

    @Bean
    public Queue objectQueue() {
        return new Queue(Constant.USER.getName());
    }


}
