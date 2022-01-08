package com.cyfhandsome.factory;

/**
 * @author cyf
 * @date 2022/1/7 23:26
 */
public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);
}
