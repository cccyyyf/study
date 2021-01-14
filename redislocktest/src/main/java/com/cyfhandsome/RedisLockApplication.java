package com.cyfhandsome;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyf
 * @date 2021/1/13 15:49
 */
@SpringBootApplication(scanBasePackages= "com.cyfhandsome.controller")
public class RedisLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class,args);
    }
}
