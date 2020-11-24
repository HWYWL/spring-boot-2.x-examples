package com.yi.tomcat.jsp.utils;

/**
 * 自定义异常
 * @author YI
 * @date 2019-1-26 14:37:15
 */
public class MyException extends RuntimeException {

	public MyException(String message) {
		super(message);
	}

}
