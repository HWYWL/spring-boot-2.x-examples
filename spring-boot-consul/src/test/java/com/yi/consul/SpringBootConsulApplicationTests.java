package com.yi.consul;

import com.yi.consul.model.Consul;
import com.yi.consul.utils.ConsulUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootConsulApplicationTests {
    @Autowired
    ConsulUtil consulUtil;

    @Test
    void test01() {
        String value = consulUtil.getValue("config/api/respon");
        System.out.println(value);
    }

    @Test
    void test02() {
        List<Consul> valAll = consulUtil.getValAll("config/api/");
        valAll.forEach(e -> System.out.println(e.getValue()));
    }

}
