package com.yi.go.fastdfs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件系统配置信息
 * @author YI
 * @date 2019-1-29 10:37:41
 */
@Component
public class ConstantProperties{

    /**
     * 文件系统url
     */
    public static String URL;

    @Value("${go.fastdfs.url}")
    public void setUrl(String url) {
        ConstantProperties.URL = url;
    }
}
