package com.yi.strategy;

import com.yi.strategy.config.HandlerContext;
import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.OrderBaseHandler;
import com.yi.strategy.service.PayBaseHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootStrategyApplicationTests {

    @Test
    public void contextLoads() {
        PayBaseHandler instance = (PayBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.ALIPAY_PAY);
        System.out.println(instance.execute(""));

        PayBaseHandler instance1 = (PayBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.WECHAT_PAY);
        System.out.println(instance1.execute(""));

        OrderBaseHandler instance2 = (OrderBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.ALIPAY_ORDER);
        System.out.println(instance2.execute(""));

        OrderBaseHandler instance3 = (OrderBaseHandler) HandlerContext.getInstance(HandlerTypeEnum.WECHAT_ORDER);
        System.out.println(instance3.execute(""));
    }
}
