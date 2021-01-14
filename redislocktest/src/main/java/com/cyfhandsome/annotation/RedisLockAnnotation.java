package com.cyfhandsome.annotation;

import com.cyfhandsome.enums.RedisLockTypeEnum;

import java.lang.annotation.*;

/**
 * @author cyf
 * @date 2021/1/13 14:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RedisLockAnnotation {
    /**
     * 特定参数识别，默认取第 0 个下标
     */
    int lockFiled() default 0;
    /**
     * 超时重试次数
     */
    int tryCount() default 3;
    /**
     * 自定义加锁类型
     */
    RedisLockTypeEnum typeEnum();
    /**
     * 释放时间，秒 s 单位
     */
    long lockTime() default 30;
}
