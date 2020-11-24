package com.yi.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: YI
 * @description:
 * @date: create in 2020/8/13 11:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class IMqttProperties {
    /**
     * mqttChannel方法名称
     */
    public static final String QUEUE = "mqttChannel";

    /**
     * 集群地址
     */
    private String serverUrls;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * 主题
     */
    private String topics;

    /**
     * 默认数据质量（0：只发一次、1：至少一次、2：最多一次）
     */
    private Integer qos;
}
