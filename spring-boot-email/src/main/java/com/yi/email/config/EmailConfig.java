package com.yi.email.config;

import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.GeneralSecurityException;

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

/**
 * 配置Email基本信息
 * @author YI
 * @date 2018-8-28 15:21:22
 */
@Configuration
public class EmailConfig {

    private static String username;
    private static String password;

    @Bean
    public String before() throws GeneralSecurityException {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(false), username, password);

        return "Successful";
    }

    @Value("${email.username}")
    public void setUsername(String username) {
        EmailConfig.username = username;
    }

    @Value("${email.password}")
    public void setPassword(String password) {
        EmailConfig.password = password;
    }
}
