package com.cyfhandsome.factory.support;

import com.cyfhandsome.factory.config.BeanDefinition;

/**
 * @author cyf
 * @date 2021/11/25 13:37
 */
public interface BeanDefinitionRegistry {

    /**
     * 像注册表注册bean
     * @param beanName beanName
     * @param beanDefinition beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
