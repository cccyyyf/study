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
