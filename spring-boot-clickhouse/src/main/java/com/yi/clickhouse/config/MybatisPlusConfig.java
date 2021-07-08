package com.yi.clickhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ORM框架
 * @author huangwenyi
 */
@Configuration
@MapperScan(basePackages = {"com.yi.clickhouse.mapper"})
public class MybatisPlusConfig {

}
