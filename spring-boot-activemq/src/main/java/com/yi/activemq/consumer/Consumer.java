package com.yi.activemq.consumer;

import com.yi.activemq.config.Config;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author YI
 * @date 2019-1-27 21:27:27
 */
@Component
public class Consumer {

	/**
	 * 使用JmsListener配置消费者监听的队列
	 * @param text	接收到的消息
	 */
	@JmsListener(destination = Config.QUEUE)
	public void receiveQueue(String text) {
		System.out.println(text);
	}

}
