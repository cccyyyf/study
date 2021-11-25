package com.cyfhandsome.test.api;

import com.cyfhandsome.springframework.config.BeanDefinition;
import com.cyfhandsome.springframework.factory.BeanFactory;
import com.cyfhandsome.springframework.service.UserService;
import org.junit.Test;

/**
 * @author cyf
 * @date 2021/11/24 17:01
 */
public class ApiTest {
    @Test
    public void test_beanFactory() {
        //初始化factory
        BeanFactory beanFactory = new BeanFactory();

        //注册
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //获取
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
    }
}

