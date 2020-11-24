package com.yi.consul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author YI
 * @date 2020/3/21 11:10
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "consul")
public class ConsulConfig {
    /**
     * 获取具体路径下的数据
     */
    private String val;
    /**
     * 目录下的所有数据
     */
    private String valAll;
}