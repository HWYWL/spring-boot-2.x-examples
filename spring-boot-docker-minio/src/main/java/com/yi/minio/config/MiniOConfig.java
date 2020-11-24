package com.yi.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 文件系统配置信息
 * @author YI
 * @date 2020-8-12 10:16:57
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio.dfs")
public class MiniOConfig {

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

    /**
     * 文件存储桶
     */
    public String bucket;

    @Bean
    public MinioClient minioClientFactory() throws Exception {

        return new MinioClient(endpoint, accessKey, secretKey);
    }
}
