package com.yi.sql.interceptor.config;

import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.yi.sql.interceptor.dao")
public class Config {

}
