package com.yi.mqtt.producer;

import com.yi.mqtt.config.IMqttProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者
 *
 * @author YI
 * @date 2019-1-27 21:27:59
 */
@Component
public class Producer {

    @Resource
    private IMqttProperties properties;

    /**
     * 绑定生产者
     * @param clientFactory
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = IMqttProperties.QUEUE)
    public MqttPahoMessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
        // 如果同时启用消费者和生产者clientId不能重复
        String clientId = properties.getClientId();
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, clientFactory);
        messageHandler.setDefaultQos(properties.getQos());
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(false);
        messageHandler.setAsyncEvents(false);
        return messageHandler;
    }

}
