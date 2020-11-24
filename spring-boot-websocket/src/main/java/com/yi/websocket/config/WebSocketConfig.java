package com.yi.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置
 * @author YI
 * @date 2018-9-27 14:48:40
 */
@Configuration
public class WebSocketConfig {
    /**
     * 当有用户连接上来之后的最小用户数，一个是本机，另一个是该用户
     */
    public static final int MINI_USERS_NUM = 2;

    /**
     * 主机核心用户(相当于群)
     */
    private static String admin;

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Value("${web.socket.config.user.admin}")
    public void setAdmin(String admin) {
        WebSocketConfig.admin = admin;
    }

    public static String getAdmin() {
        return admin;
    }
}