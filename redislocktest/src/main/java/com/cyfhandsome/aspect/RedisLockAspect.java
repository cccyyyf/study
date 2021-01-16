package com.cyfhandsome.aspect;

import com.cyfhandsome.annotation.RedisLockAnnotation;
import com.cyfhandsome.enums.RedisLockTypeEnum;
import com.cyfhandsome.exception.ErrorException;
import com.cyfhandsome.modol.RedisLockTestHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cyf
 * @date 2021/1/13 14:59
 */
@Aspect
@Configuration
public class RedisLockAspect {

    @Resource
    private RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisLockAspect.class);

    private static final ScheduledExecutorService SCHEDULER =
            new ScheduledThreadPoolExecutor(1,
                    new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());


    /**
     * 存储目前有效的key定义
     */
    private static ConcurrentLinkedQueue<RedisLockTestHolder> holderList = new ConcurrentLinkedQueue();

    {
        SCHEDULER.scheduleAtFixedRate(() -> {
            // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
            Iterator<RedisLockTestHolder> iterator = holderList.iterator();
            while (iterator.hasNext()) {
                RedisLockTestHolder holder = iterator.next();
                // 判空
                if (holder == null) {
                    iterator.remove();
                    continue;
                }

                // 判断 key 是否还有效，无效的话进行移除
                if (redisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
                    iterator.remove();
                    continue;
                }

                // 超时重试次数，超过时给线程设定中断
                if (holder.getCurrentCount() > holder.getTryCount()) {
                    holder.getCurrentTread().interrupt();
                    iterator.remove();
                    continue;
                }
                // 判断是否进入最后三分之一时间
                long curTime = System.currentTimeMillis();
                boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                if (shouldExtend) {
                    holder.setLastModifyTime(curTime);
                    redisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
                    log.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentCount());
                    holder.setCurrentCount(holder.getCurrentCount() + 1);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    /**
     * 获取指定类上的指定方法
     *
     * @param clazz          指定类
     * @param name           指定方法
     * @param parameterTypes 参数类型列表
     * @return 找到就返回method，否则返回null
     */
    public static Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethodFor(superClass, name, parameterTypes);
            }
        }
        return null;
    }

    /**
     * 解析方法
     *
     * @param pjp
     * @return
     */
    private Method resolveMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class<?> targetClass = pjp.getTarget().getClass();

        Method method = getDeclaredMethodFor(
                targetClass,
                signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("不能解析当前方法： " + signature.getMethod().getName());
        }
        return method;
    }



    @Pointcut("@annotation(com.cyfhandsome.annotation.RedisLockAnnotation)")
    public void redisLockPc() {
    }

    @Around(value = "redisLockPc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("测试测试");
        // 解析参数
        Method method = resolveMethod(pjp);
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        RedisLockTypeEnum typeEnum = annotation.typeEnum();
        Object[] params = pjp.getArgs();
        String ukString = params[annotation.lockFiled()].toString();
        // 省略很多参数校验和判空
        String businessKey = typeEnum.getUniqueKey(ukString);
        String uniqueValue = UUID.randomUUID().toString();
        // 加锁
        Object result = null;

        try {
            boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue);
            if (!isSuccess) {
                throw new ErrorException("正在操作，请稍后再试");
            }
            redisTemplate.expire(businessKey, annotation.lockTime(), TimeUnit.SECONDS);
            Thread currentThread = Thread.currentThread();
            // 将本次 Task 信息加入「延时」队列中
            holderList.add(new RedisLockTestHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(),
                    currentThread, annotation.tryCount()));
            // 执行业务操作
            result = pjp.proceed();
            // 线程被中断，抛出异常，中断此次请求
            if (currentThread.isInterrupted()) {
                throw new InterruptedException("中断请求");
            }
        } catch (InterruptedException e) {
            log.error("出现异常", e);
        } catch (Exception e) {
            log.error("出现错误", e);
        } finally {
            // 请求结束后，强制删掉 key，释放锁
            redisTemplate.delete(businessKey);
            log.info("删除redis锁, businessKey 是 [" + businessKey + "]");
        }
        return result;
    }
}
