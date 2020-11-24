package com.yi.strategy.service.impl;

import com.yi.strategy.annotations.HandlerType;
import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.OrderBaseHandler;
import org.springframework.stereotype.Service;

/**
 * 微信订单业务
 *
 * @author YI
 * @date 2019-10-8
 */
@Service
@HandlerType(HandlerTypeEnum.WECHAT_ORDER)
public class WeChatOrderHandler implements OrderBaseHandler {
    @Override
    public Object execute(Object param) {
        return "微信订单";
    }
}
