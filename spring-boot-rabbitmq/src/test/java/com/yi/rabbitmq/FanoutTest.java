package com.yi.rabbitmq;

import com.yi.rabbitmq.fanout.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 广播发送接收
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {
    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void hello() throws Exception {
        fanoutSender.send();
    }
}
