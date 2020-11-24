package com.yi.version.annotations;

import java.lang.annotation.*;

/**
 * 版本号注解
 *
 * @author HWY
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 标识版本号，默认为0
     */
    int value() default 1;
}
