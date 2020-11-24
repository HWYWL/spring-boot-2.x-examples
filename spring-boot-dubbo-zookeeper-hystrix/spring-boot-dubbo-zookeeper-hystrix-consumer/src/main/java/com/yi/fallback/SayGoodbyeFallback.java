package com.yi.fallback;

import com.netease.hystrix.dubbo.rpc.filter.Fallback;

/**
 * SayGoodbye 的熔断
 * @author YI
 * @date 2018-9-7 15:04:42
 */
public class SayGoodbyeFallback implements Fallback {
    @Override
    public Object invoke() {
        System.out.println("SayGoodbye 方法的熔断！");
        return null;
    }
}
