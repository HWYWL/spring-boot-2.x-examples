package com.yi.jetty.jsp.controller;

import com.yi.jetty.jsp.utils.MessageResult;
import com.yi.jetty.jsp.utils.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 控制接口
 * @author YI
 * @date 2019-1-26 14:37:49
 */
@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		LocalDateTime ldt = LocalDateTime.now();
		model.put("time", ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		model.put("message", this.message);
		return "welcome";
	}

	/**
	 * 统一异常处理
	 * @return
	 */
	@RequestMapping("/fail")
	public String fail() {
		throw new MyException("Oh dear!");
	}

	@ExceptionHandler(MyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody MessageResult handleMyRuntimeException(MyException exception) {
		return MessageResult.ok("你好呀");
	}

}
