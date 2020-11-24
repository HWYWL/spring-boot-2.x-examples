package com.yi.rabbitmq;

import com.yi.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 通用匹配发送接收
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void send() throws Exception {
        topicSender.send();
    }

    @Test
    public void send1() throws Exception {
        topicSender.send1();
    }

    @Test
    public void send2() throws Exception {
        topicSender.send2();
    }
}
