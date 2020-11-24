package com.yi.jetty.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 逻辑处理
 * @author YI
 * @date 2019-1-26 11:43:57
 */
@Service
public class HelloWorldService {

	@Value("${name:World}")
	private String name;

	public String getHelloMessage() {
		return "Hello " + this.name;
	}

}
