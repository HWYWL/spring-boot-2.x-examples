package com.yi.redshift.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2019-12-20
 */
@Configuration
@MapperScan(basePackages = {"com.yi.redshift.mapper"})
public class Config {

}
