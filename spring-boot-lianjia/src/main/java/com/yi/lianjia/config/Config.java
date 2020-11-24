package com.yi.lianjia.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2019年12月26日
 */
@Configuration
@MapperScan(basePackages = {"com.yi.lianjia.mapper"})
public class Config {
    /**
     * 页面地址
     */
    public static final String PRON_URL = "https://gz.lianjia.com/ershoufang/pg{page}";
}
