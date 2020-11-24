package com.yi.async.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 配置扫描接口路径,开启异步接口
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
@EnableAsync
@MapperScan(basePackages = {"com.yi.async.mapper"})
public class Config {

}
