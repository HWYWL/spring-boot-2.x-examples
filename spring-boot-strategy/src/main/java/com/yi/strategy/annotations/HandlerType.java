package com.yi.strategy.annotations;

import com.yi.strategy.model.HandlerTypeEnum;

import java.lang.annotation.*;

/**
 * 标记子类注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    HandlerTypeEnum value();
}
