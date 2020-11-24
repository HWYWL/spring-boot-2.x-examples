package com.yi.jetty.web;

import com.yi.jetty.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 请求接口
 * @author YI
 * @date 2019-1-26 11:42:46
 */
@Controller
public class SampleController {

	@Autowired
	private HelloWorldService helloWorldService;

	/**
	 * http://localhost:8080/
	 * @return String
	 */
	@GetMapping("/")
	@ResponseBody
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}

}
