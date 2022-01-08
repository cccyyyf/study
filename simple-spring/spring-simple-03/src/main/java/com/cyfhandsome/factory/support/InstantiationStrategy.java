package com.cyfhandsome.factory.support;

import com.cyfhandsome.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author cyf
 * @date 2022/1/8 10:16
 */
public interface InstantiationStrategy {
    /**
     * 实例化策略接口
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args);
}
