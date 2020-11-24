package com.yi.druid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis操作数据库,使用Ali的Druid连接池
 * 访问 http://localhost:8080/druid/index.html 可查看监控
 * @author YI
 * @date 2018-8-22 18:31:07
 */
@SpringBootApplication
public class SpringBootMybatisDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisDruidApplication.class, args);
    }
}
