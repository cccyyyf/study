package com.cyfhandsome.springframework.config;

/**
 * @author cyf
 * @date 2021/11/24 15:27
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }
}
