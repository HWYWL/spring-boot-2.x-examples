package com.yi.auto.code.ui.config;

import com.zengtengpeng.autoCode.config.GlobalConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加载配置
 *
 * @author YI
 * @date 2019-4-9 16:34:15
 */
@ConfigurationProperties(prefix = "auto-code")
public class AutoCodeProperties extends GlobalConfig {
}
