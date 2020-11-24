package com.yi.memcached;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Memcache缓存
 * @author YI
 * @date 2018-10-16 15:23:23
 */
@SpringBootApplication
public class SpringBootMemcachedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMemcachedApplication.class, args);
    }
}
