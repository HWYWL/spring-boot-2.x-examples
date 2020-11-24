package com.yi.beatlsql.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author YI
 * @date 2019-3-5 21:58:24
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "datasource")
    public DataSource dataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

        return ds;
    }
}
