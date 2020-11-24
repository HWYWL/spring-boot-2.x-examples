package com.yi.hikari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis操作数据库,使用HikariCP连接池
 * @author YI
 * @date 2018-8-22 18:31:07
 */
@SpringBootApplication
public class SpringBootMybatisHikariApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisHikariApplication.class, args);
    }
}
