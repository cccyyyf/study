package com.cyfhandsome.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author cyf
 * @date 2021/1/14 10:20
 */
@Aspect
@Configuration
@Slf4j
public class WebLogAspect {

    @Pointcut("@annotation(com.cyfhandsome.annotation.WebLog)")
    public void webLog(){}

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long millis = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();

        log.info("打印出参 : {}" , new Gson().toJson(proceed));
        log.info("消耗时间 ： {}",System.currentTimeMillis() - millis);
        return proceed;
    }

}
