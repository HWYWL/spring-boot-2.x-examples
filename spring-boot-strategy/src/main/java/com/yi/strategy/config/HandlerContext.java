package com.yi.strategy.config;

import com.yi.strategy.model.HandlerTypeEnum;
import com.yi.strategy.service.BaseHandler;
import com.yi.strategy.utils.BeanUtil;

import java.util.Map;
import java.util.Objects;

/**
 * 类加载处理器
 *
 * @author YI
 * @date 2019-10-8
 */
public class HandlerContext {
    private static Map<HandlerTypeEnum, Class> handlerMap;

    HandlerContext(Map<HandlerTypeEnum, Class> handlerMap) {
        HandlerContext.handlerMap = handlerMap;
    }

    public static BaseHandler getInstance(HandlerTypeEnum type) {
        Class clazz = handlerMap.get(type);
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return (BaseHandler) BeanUtil.getBean(clazz);
    }
}