package com.yi.strategy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 处理器类型枚举
 *
 * @author YI
 * @date 2019-10-8
 */
@Getter
@AllArgsConstructor
public enum HandlerTypeEnum {
    /**
     * 支付类型和订单
     */
    WECHAT_PAY(10001,"微信支付"),
    ALIPAY_PAY(10002,"支付宝支付"),
    WECHAT_ORDER(20001,"微信订单"),
    ALIPAY_ORDER(20002,"支付宝订单");

    private Integer code;
    private String desc;
}
