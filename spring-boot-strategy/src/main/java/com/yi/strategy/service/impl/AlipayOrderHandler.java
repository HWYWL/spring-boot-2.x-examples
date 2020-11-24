package com.yi.strategy.service.impl;

import com.yi.strategy.annotations.HandlerType;
import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.OrderBaseHandler;
import org.springframework.stereotype.Service;

/**
 * 支付宝订单业务
 *
 * @author YI
 * @date 2019-10-8
 */
@Service
@HandlerType(HandlerTypeEnum.ALIPAY_ORDER)
public class AlipayOrderHandler implements OrderBaseHandler {
    @Override
    public Object execute(Object param) {
        return "支付宝订单";
    }
}
