package com.yi.token.config;

import org.springframework.context.annotation.Configuration;

/**
 * 配置扫描接口路径
 * @author YI
 * @date 2018-8-22 18:19:45
 */
@Configuration
public class Config {
    /**
     * redis key
     */
    public static final String USER_REDIS_SESSION = "user_redis_session";

    /**
     * redis token超时时间（ms）
     */
    public static final int REDIS_TIMEOUT = 1000 * 60 * 30;
}
