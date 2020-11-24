package com.yi.rabbitmq.fanout;

import com.yi.rabbitmq.common.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广播模式 忽略key
 * @author YI
 * @date 2018-8-16 17:10:37
 */
@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hi, fanout msg ";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(Constant.FANOUTEXCHANGE.getName(),"", context);
	}

}