package com.yi.presto.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * presto 连接配置
 *
 * @author YI
 * @date 2019-10-9
 */
@Configuration
@Data
public class HiveDruidConfig {
    @Bean(name = "prestoDatasource")
    @ConfigurationProperties(prefix = "presto.datasource")
    public DataSource prestoDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "prestoJdbcTemplate")
    public JdbcTemplate prestoJdbcTemplate(DataSource prestoDatasource) {
        return new JdbcTemplate(prestoDatasource);
    }
}

