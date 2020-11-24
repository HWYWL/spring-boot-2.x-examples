package com.yi.contiperf;

import com.yi.contiperf.service.ContiperfExampleService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootContiperfApplicationTests {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Autowired
    private ContiperfExampleService contiperfExampleService;

    @Test
    @PerfTest(threads = 1000, duration = 1500)
    public void findAll() {
        contiperfExampleService.findAll().forEach(System.out::println);
    }
}
