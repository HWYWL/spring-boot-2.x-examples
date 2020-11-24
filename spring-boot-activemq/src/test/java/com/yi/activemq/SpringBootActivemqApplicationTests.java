package com.yi.activemq;

import com.yi.activemq.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootActivemqApplicationTests {
    @Autowired
    private Producer producer;

    @Test
    public void sendSimpleMessage() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            LocalDateTime ldt = LocalDateTime.now();
            this.producer.sendMessage("消息队列测试：" + ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Thread.sleep(500L);
        }
    }

}

