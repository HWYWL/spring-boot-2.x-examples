package com.yi.aop.annotation.log.aspect;

import java.lang.annotation.*;

/**
 * 注解apo拦截
 *
 * @author YI
 * @date 2019年4月29日16:33:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 接口描述
     *
     * @return
     */
    String description() default "";
}
