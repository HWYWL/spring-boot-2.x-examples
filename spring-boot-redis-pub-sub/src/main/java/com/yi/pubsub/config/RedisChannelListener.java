package com.yi.pubsub.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Redis的Pub/Sub功能 此方法用来订阅频道的消息
 * @author YI
 * @date 2018-9-6 16:00:56
 */
public class RedisChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();

        try {
            String content = new String(body, "UTF-8");
            String getChannel = new String(channel, "UTF-8");
            StringBuffer buffer = new StringBuffer().append("得到来自订阅-->").append(getChannel).append(" 的数据为-->").append(content);

            System.out.println(buffer.toString());
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
