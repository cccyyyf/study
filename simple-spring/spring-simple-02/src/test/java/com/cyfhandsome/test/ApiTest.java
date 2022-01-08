package com.cyfhandsome.test;

import com.cyfhandsome.factory.config.BeanDefinition;
import com.cyfhandsome.factory.service.UserService;
import com.cyfhandsome.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author cyf
 * @date 2021/11/25 14:54
 */
public class ApiTest {
    /**
     * 执行步骤
     * 1、创建defaultListableBeanFactory 初始化 beanDefinitionMap 和 singletonObjects
     * 2、将对象注入到beanDefinition
     * 3、将bean注册到beanDefinitionMap中
     * 4、获取bean
     *   4.1、从singletonObjects中获取bean对象 获取到直接返回 获取不到到4.2
     *   4.2、从beanDefinitionMap获取到beanDefinition
     *   4.3、实例化beanDefinition 并将bean注册到singletonObjects中返回
     * 5、下次获取bean的时候直接从singletonObjects中获取
     */
    @Test
    public void testBeanFactory(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registryBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService)defaultListableBeanFactory.getBean("userService");
        userService.getUserInfo();

        UserService userService1 =(UserService) defaultListableBeanFactory.getBean("userService");
        userService1.getUserInfo();
    }
}
