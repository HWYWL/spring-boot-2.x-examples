package com.yi.tomcat.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Tomcat容器执行jsp
 * @author YI
 * @date 2019-1-26 14:35:23
 */
@SpringBootApplication
public class SpringBootTomcatJspApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootTomcatJspApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTomcatJspApplication.class, args);
	}

}

