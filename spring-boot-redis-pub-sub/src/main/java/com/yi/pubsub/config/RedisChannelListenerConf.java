package com.yi.pubsub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * 监听Redis中订阅的消息
 * @author YI
 * @date 2018-9-6 16:00:51
 */
@Configuration
public class RedisChannelListenerConf {
    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter() {
        //监听序列化
        MessageListenerAdapter adapter = new MessageListenerAdapter(new RedisChannelListener());

        adapter.setSerializer(new JdkSerializationRedisSerializer());

        return adapter;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        //订阅所有news.* 频道内容,通过PatternTopic派发消息给对应的消息监听者
        container.addMessageListener(listenerAdapter, new PatternTopic("news.*"));

        return container;
    }
}
