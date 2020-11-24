package com.yi.memcached.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * io配置
 * @author YI
 * @date 2018-10-16 15:20:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "memcache")
public class SockIOPoolConfig {
    private String[] servers;

    private Integer[] weights;

    private int initConn;

    private int minConn;

    private int maxConn;

    private long maintSleep;

    private boolean nagle;

    private int socketTO;
}
