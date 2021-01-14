package com.cyfhandsome.annotation;

import java.lang.annotation.*;

/**
 * @author cyf
 * @date 2021/1/14 10:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
