package com.yi.exception.handler;

import com.yi.exception.exception.JsonException;
import com.yi.exception.exception.PageException;
import com.yi.exception.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author YI
 * @date 2020-7-22
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 统一 json 异常处理
	 *
	 * @param exception JsonException
	 * @return 统一返回 json 格式
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = JsonException.class)
	@ResponseBody
	public ApiResponse jsonErrorHandler(HttpServletRequest req, JsonException exception) {
		log.error("【JsonException】:{}", exception.getMessage());
		return ApiResponse.ofException(exception);
	}

	/**
	 * 统一 页面 异常处理
	 *
	 * @param exception PageException
	 * @return 统一跳转到异常页面
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(HttpServletRequest req,PageException exception) {
		log.error("【PageException】:{}", exception.getMessage());
		ModelAndView view = new ModelAndView();
		view.addObject("url", req.getRequestURL());
		view.addObject("message", exception.getMessage());
		view.setViewName(DEFAULT_ERROR_VIEW);
		return view;
	}
}
