package com.yi.storm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis配置
 *
 * @author huangwenyi
 * @date 2019-9-3
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    public static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisConfig.stringRedisTemplate = stringRedisTemplate;
    }
}
