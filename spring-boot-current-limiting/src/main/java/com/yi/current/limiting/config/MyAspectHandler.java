package com.yi.current.limiting.config;

import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentAspectHandler;
import cn.yueshutong.springbootstartercurrentlimiting.method.annotation.CurrentLimiter;
import com.yi.current.limiting.utils.MessageResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 针对被注解的方法进行自定义拒绝策略
 *
 * @author YI
 * @date 2019-4-27 12:30:53
 */
@Component
public class MyAspectHandler implements CurrentAspectHandler {
    @Override
    public Object around(ProceedingJoinPoint pjp, CurrentLimiter rateLimiter) throws Throwable {
        return MessageResult.errorMsg("当前服务不可用");
    }
}
