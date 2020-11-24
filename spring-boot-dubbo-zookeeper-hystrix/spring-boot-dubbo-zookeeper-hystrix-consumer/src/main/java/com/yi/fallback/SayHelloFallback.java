package com.yi.fallback;

import com.netease.hystrix.dubbo.rpc.filter.Fallback;

/**
 * SayHello 的熔断
 * @author YI
 * @date 2018-9-7 15:04:42
 */
public class SayHelloFallback implements Fallback {
    @Override
    public Object invoke() {
        System.out.println("SayHello 方法的熔断！");
        return null;
    }
}
