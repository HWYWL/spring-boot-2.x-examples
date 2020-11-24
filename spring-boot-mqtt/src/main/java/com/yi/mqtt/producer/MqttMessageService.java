package com.yi.mqtt.producer;

import com.yi.mqtt.config.IMqttProperties;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author: YI
 * @description: 发送消息
 * @date: create in 2020/8/13 11:31
 */
@MessagingGateway(defaultRequestChannel = IMqttProperties.QUEUE)
public interface MqttMessageService {
    void sendToMessage(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToMessage(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    void sendToMessage(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, @Header(MqttHeaders.RETAINED) String retain, String payload);
}
