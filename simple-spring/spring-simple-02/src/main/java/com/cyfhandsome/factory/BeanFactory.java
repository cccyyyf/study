package com.cyfhandsome.factory;

/**
 * @author cyf
 * @date 2021/11/24 18:38
 * 定义基础模板类
 */
public interface BeanFactory {
    Object getBean(String beanName);
}
