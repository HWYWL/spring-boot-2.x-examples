package com.yi.douban.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2019年12月26日
 */
@Configuration
@MapperScan(basePackages = {"com.yi.douban.mapper"})
public class Config {
    /**
     * 页面地址
     */
    public static final String PRON_URL = "https://movie.douban.com/subject/26794435/reviews?start={start}";
}
