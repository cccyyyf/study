package com.cyfhandsome.factory.support;

import com.cyfhandsome.factory.BeanFactory;
import com.cyfhandsome.factory.config.BeanDefinition;

/**
 * @author cyf
 * @date 2021/11/24 18:31
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return this.doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName,Object... args){
        return this.doGetBean(beanName,args);
    }

    public <T> T doGetBean(String beanName,Object[] args){
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) this.createBean(beanName, beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args);
}
