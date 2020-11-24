package com.yi.strategy.service.impl;

import com.yi.strategy.annotations.HandlerType;
import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.PayBaseHandler;
import org.springframework.stereotype.Service;

/**
 * 微信支付业务
 *
 * @author YI
 * @date 2019-10-8
 */
@Service
@HandlerType(HandlerTypeEnum.WECHAT_PAY)
public class WeChatPayHandler implements PayBaseHandler {

    @Override
    public Object execute(Object param) {
        return "微信支付";
    }
}
