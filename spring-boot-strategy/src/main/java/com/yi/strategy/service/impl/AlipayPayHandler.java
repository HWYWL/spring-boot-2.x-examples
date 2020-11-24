package com.yi.strategy.service.impl;

import com.yi.strategy.annotations.HandlerType;
import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.PayBaseHandler;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付业务
 *
 * @author YI
 * @date 2019-10-8
 */
@Service
@HandlerType(HandlerTypeEnum.ALIPAY_PAY)
public class AlipayPayHandler implements PayBaseHandler {

    @Override
    public Object execute(Object param) {
        return "支付宝支付";
    }
}
