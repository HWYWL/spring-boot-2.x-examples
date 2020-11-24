package com.yi.mqtt;

import com.yi.mqtt.config.IMqttProperties;
import com.yi.mqtt.producer.MqttMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootMqttApplicationTests {
    @Resource
    private MqttMessageService messageService;
    @Resource
    private IMqttProperties iMqttProperties;

    @Test
    void contextLoads() {
        messageService.sendToMessage(iMqttProperties.getTopics(), "Hello MQTT!!!");
    }

}
