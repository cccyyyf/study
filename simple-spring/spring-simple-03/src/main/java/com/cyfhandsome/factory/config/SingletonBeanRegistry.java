package com.cyfhandsome.factory.config;

/**
 * @author cyf
 * @date 2021/11/24 18:26
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
