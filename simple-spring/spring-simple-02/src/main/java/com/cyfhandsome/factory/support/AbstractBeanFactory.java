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
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return this.createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
