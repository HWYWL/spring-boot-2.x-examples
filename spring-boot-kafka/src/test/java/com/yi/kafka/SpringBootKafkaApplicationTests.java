package com.yi.kafka;

import com.yi.kafka.message.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKafkaApplicationTests {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void test1() {

        kafkaProducer.sendMessage("topic-1", "topic--------1");
        kafkaProducer.sendMessage("topic-2", "topic--------2");
    }

}
