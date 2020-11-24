package com.yi.sharding.sphere.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2019-6-3
 */
@Configuration
@MapperScan(basePackages = {"com.yi.sharding.sphere.dao"})
public class Config {

}
