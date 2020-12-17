package com.yi.erupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import xyz.erupt.core.annotation.EruptScan;

/**
 * 自动化前端管理
 *
 * @author huangwenyi
 */
@SpringBootApplication
@ComponentScan({"xyz.erupt", "com.yi.erupt"})
@EntityScan({"xyz.erupt", "com.yi.erupt"})
@EruptScan({"xyz.erupt", "com.yi.erupt"})
public class SpringBootEruptApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEruptApplication.class, args);
    }

}
