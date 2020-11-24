package com.yi.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author huangwenyi
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitorApplication.class, args);
    }
}
