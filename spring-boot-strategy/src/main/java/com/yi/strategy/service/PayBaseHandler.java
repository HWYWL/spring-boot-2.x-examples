package com.yi.strategy.service;

/**
 * 模拟支付接口
 *
 * @author YI
 * @date 2019-10-8
 */
public interface PayBaseHandler extends BaseHandler{
    Object execute(Object param);
}
