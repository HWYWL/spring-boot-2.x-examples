package com.yi.sa;

import cn.dev33.satoken.SaTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangwenyi
 */
@SpringBootApplication
public class SpringBootSaTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSaTokenApplication.class, args);
        System.out.println("启动成功：sa-token配置如下：" + SaTokenManager.getConfig());
    }

}
