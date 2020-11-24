package com.yi.anima.config;

import io.github.biezhi.anima.Anima;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置文件
 *
 * @author YI
 * @date 2019-1-22 16:22:00
 */
@Configuration
public class Config {
    @Resource
    private DataSource dataSource;

    @Bean
    public Anima anima(){
        return new Anima(dataSource);
    }
}
