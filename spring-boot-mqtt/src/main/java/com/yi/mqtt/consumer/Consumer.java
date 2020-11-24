package com.yi.mqtt.consumer;


import com.yi.mqtt.config.IMqttClient;
import com.yi.mqtt.config.IMqttProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 消费者
 *
 * @author YI
 * @date 2020-8-13 11:26:45
 */
@Component
public class Consumer {

	@Resource
	private IMqttProperties properties;
	@Resource
	private IMqttClient client;

	/**
	 * 绑定消费者
	 * @return
	 */
	@Bean
	@ServiceActivator(inputChannel = IMqttProperties.QUEUE)
	public MessageHandler handler() {
		// 接收消息
		return message -> {
			Map<String, Object> msgMap = new HashMap<>();
			MessageHeaders headers = message.getHeaders();
			for (String key : headers.keySet()) {
				msgMap.put(key, headers.get(key));
			}
			msgMap.put("content", message.getPayload());
			System.out.println("接收到信息：" + msgMap.toString());
		};
	}

	@Bean
	public MessageProducerSupport mqttInbound() {
		// 如果同时启用消费者和生产者clientId不能重复
		String clientId = properties.getClientId();
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId, client.mqttClientFactory(), properties.getTopics());
		adapter.setQos(properties.getQos());
		adapter.setOutputChannel(client.mqttChannel());
		adapter.setConverter(new DefaultPahoMessageConverter());
		return adapter;
	}

}
