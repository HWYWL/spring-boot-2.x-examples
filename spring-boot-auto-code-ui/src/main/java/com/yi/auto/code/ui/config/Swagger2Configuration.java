package com.yi.auto.code.ui.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

/**
 * Swagger2 接口文档
 *
 * @author YI
 * @date 2019-4-9 16:34:37
 */
@Configuration
@EnableConfigurationProperties(value = AutoCodeProperties.class)
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(prefix = "auto-code",name = "swagger",havingValue = "true", matchIfMissing = true)
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("auto-code接口(swagger 目前有BUG,参数如果是实体类,设置忽略该参数不起作用.所以请忽略下面 (*.*) 带点的参数,这些参数不会被使用)")
                //创建人
                .contact(new Contact("YI", "https://gitee.com/ztp/auto-code", "744489075@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API描述")
                .build();
    }
}