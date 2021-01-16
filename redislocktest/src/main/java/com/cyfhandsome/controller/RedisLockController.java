package com.cyfhandsome.controller;

import com.cyfhandsome.annotation.RedisLockAnnotation;
import com.cyfhandsome.annotation.WebLog;
import com.cyfhandsome.enums.RedisLockTypeEnum;
import com.cyfhandsome.exception.ErrorException;
import com.cyfhandsome.modol.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

/**
 * @author cyf
 * @date 2021/1/13 15:47
 */
@RestController
@RequestMapping("/test")
public class RedisLockController {

    private static final Logger log = LoggerFactory.getLogger(RedisLockController.class);


    @GetMapping("/test/{id}")
    @WebLog(description = "请求了用户接口")
    public String test(@PathVariable String id){
        return id;
    }

    @GetMapping("/testException")
    @WebLog(description = "测试自定义异常")
    public RestResult testException(){
        return RestResult.error("测试");
    }

    @GetMapping("/testRedisLock")
    @WebLog(description = "测试reids锁")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public RestResult testRedisLock(@RequestParam("userId") Long userId) {
        try {
            log.info("睡眠执行前");
            Thread.sleep(10000);
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }
        return RestResult.success();
    }
}
