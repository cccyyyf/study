package com.cyfhandsome.factory.support;

import com.cyfhandsome.factory.BeanException;
import com.cyfhandsome.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyf
 * @date 2021/11/25 13:28
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeanException("No bean Named '" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
