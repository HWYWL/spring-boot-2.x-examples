package com.yi.nacos.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author YI
 * @date 2020/3/21 11:10
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "conf")
public class NacosConfig {
    /**
     * 获取配置/发布配置/删除配置 URL
     */
    private String configs;
    /**
     * 监听配置 url
     */
    private String listener;
}