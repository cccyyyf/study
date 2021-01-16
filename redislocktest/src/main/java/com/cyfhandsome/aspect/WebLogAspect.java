package com.cyfhandsome.aspect;

import com.cyfhandsome.annotation.WebLog;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author cyf
 * @date 2021/1/14 10:20
 */
@Aspect
@Component
@Slf4j
//@Profile({"*"})

//ttttry

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

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        //获取注解描述
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========================================== 开始 ==========================================");
        // 打印请求 url
        log.info("请求的URL   : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("描述信息    : {}", methodDescription);
        // 打印 Http method
        log.info("请求方式    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("请求路径    : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP         : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("入参       : {}", new Gson().toJson(joinPoint.getArgs()));

        //可以添加数据库中的操作日志，重要信息日志
    }

    @After("webLog()")
    public void doAfter(){
        log.info("========================================== 结束 ==========================================");
    }

    /**
     * 获取注解描述
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private String getAspectLogDescription(JoinPoint joinPoint) throws Throwable {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }


}
