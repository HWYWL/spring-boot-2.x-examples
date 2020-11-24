package com.yi.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * xml配置
 *
 * @author YI
 * @date 2019-1-28 10:21:24
 */
@SpringBootApplication
public class SpringBootXmlApplication {

    private static final String CONTEXT_XML = "classpath:/META-INF/application-context.xml";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setSources(Collections.singleton(CONTEXT_XML));
        application.run(args);
    }

}

