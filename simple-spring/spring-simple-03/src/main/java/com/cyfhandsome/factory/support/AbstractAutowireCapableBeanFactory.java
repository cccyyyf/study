package com.cyfhandsome.factory.support;

import com.cyfhandsome.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author cyf
 * @date 2021/11/25 11:00
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName,beanDefinition,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建bean对象
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object... args) {
        Constructor constructor = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);

    }

    /**
     * 获取策略化对象
     * @return
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置策略化对象
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
