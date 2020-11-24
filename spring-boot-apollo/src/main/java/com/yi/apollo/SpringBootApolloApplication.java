package com.yi.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class SpringBootApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApolloApplication.class, args);
    }

}
