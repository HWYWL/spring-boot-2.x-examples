package com.yi.auto.code.ui.config;

import com.zengtengpeng.autoCode.config.AutoCodeConfig;
import com.zengtengpeng.ui.controller.GlobalController;
import com.zengtengpeng.ui.controller.RelationController;
import com.zengtengpeng.ui.controller.SimpleController;
import com.zengtengpeng.ui.servlet.AutoCodeUiServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * autoCode配置
 *
 * @author YI
 * @date 2019-4-9 16:34:47
 */
@Configuration
@EnableConfigurationProperties(value = AutoCodeProperties.class)
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(prefix = "auto-code",name = "autoCode",havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(DataSource.class)
public class AutoCodeConfiguration {

    @Autowired
    private AutoCodeProperties autoCodeProperties;
    @Bean
    @ConditionalOnMissingBean(GlobalController.class)
    public GlobalController globalController() {
        return new GlobalController();
    }

    @Bean
    @ConditionalOnMissingBean(AutoCodeConfig.class)
    public AutoCodeConfig autoCodeConfig() {
        AutoCodeConfig autoCodeConfig=new AutoCodeConfig();
        autoCodeConfig.setGlobalConfig(autoCodeProperties);
        return autoCodeConfig;
    }


    @Bean
    @ConditionalOnMissingBean(SimpleController.class)
    public SimpleController simpleController() {
        return new SimpleController();
    }

    @Bean
    @ConditionalOnMissingBean(RelationController.class)
    public RelationController relationController() {
        return new RelationController();
    }

    @Bean
    public ServletRegistrationBean<AutoCodeUiServlet> servletRegistrationBean() {
        AutoCodeUiServlet autoCodeUiServlet = new AutoCodeUiServlet();
        return  new ServletRegistrationBean<>(autoCodeUiServlet,"/auto-code-ui/ui/*","/auto-code-ui/static/*");

    }

}