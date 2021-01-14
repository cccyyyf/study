package com.cyfhandsome.controller;

import com.cyfhandsome.annotation.RedisLockAnnotation;
import com.cyfhandsome.annotation.WebLog;
import com.cyfhandsome.enums.RedisLockTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

/**
 * @author cyf
 * @date 2021/1/13 15:47
 */
@RestController
@RequestMapping("/test")
public class RedisLockController {

    private static final Logger log = LoggerFactory.getLogger(RedisLockController.class);


    @GetMapping("/test")
    @WebLog(description = "请求了用户接口")
    public String test(){
        return "12334";
    }

    @GetMapping("/testRedisLock")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public Book testRedisLock(@RequestParam("userId") Long userId) {
        try {
            /*log.info("睡眠执行前");
            Thread.sleep(10000);*/
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }
        return null;
    }
}
