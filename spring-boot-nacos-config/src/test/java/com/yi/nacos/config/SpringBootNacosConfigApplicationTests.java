package com.yi.nacos.config;

import com.yi.nacos.config.utils.NaCosUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootNacosConfigApplicationTests {
    @Autowired
    NaCosUtil naCosUtil;

    @Test
    void test01() {
        String properties = naCosUtil.getNacosConfig("ll.properties", "DEFAULT_GROUP");
        String json = naCosUtil.getNacosConfig("OLConsumer", "HWY_GROUP");

        System.out.println(properties);
        System.out.println("************************************************************************");
        System.out.println(json);
    }
}
