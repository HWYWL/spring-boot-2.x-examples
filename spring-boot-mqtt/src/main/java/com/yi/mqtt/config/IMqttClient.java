package com.yi.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @author: YI
 * @description:
 * @date: create in 2020/8/13 11:04
 */
@Configuration
public class IMqttClient {
    @Resource
    private IMqttProperties properties;

    /**
     * 连接activemq
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        String[] urls = {properties.getServerUrls()};
        // 连接activemq
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(urls);
        options.setCleanSession(false);

        // 在客户端连接出现异常的情况下,由服务器主动发布此消息
        // options.setWill();

        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttChannel() {
        return new DirectChannel();
    }
}
