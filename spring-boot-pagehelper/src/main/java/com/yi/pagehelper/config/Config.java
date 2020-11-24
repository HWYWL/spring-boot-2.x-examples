package com.yi.pagehelper.config;

import org.springframework.context.annotation.Configuration;

/**
 * 通用配置
 * @author YI
 * @date 2018-12-16 11:25:32
 */
@Configuration
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.yi.pagehelper.dao")
public class Config {

}
