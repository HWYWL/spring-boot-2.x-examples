package com.yi.redisson.lock.cinfig;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 配置Redisson
 * @author YI
 * @date 2018-11-29 16:58:20
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() throws IOException {
        // 读取yaml格式的配置文件
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson.yaml"));
        return Redisson.create(config);
    }
}