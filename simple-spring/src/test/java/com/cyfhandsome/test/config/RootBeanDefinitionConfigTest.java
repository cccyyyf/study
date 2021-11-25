package com.cyfhandsome.test.config;

import com.cyfhandsome.test.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyf
 * @date 2021/11/24 15:46
 */
public class RootBeanDefinitionConfigTest {
    public static void main(String[] args) {
        //在 XML 中定义的各种属性，就是先被解析到 BeanDefinition 中，然后再注册到 Spring 容器中去，最后拿到我们需要的 Bean。
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("username","test-cyf");
        pvs.add("address","com.cyfhandsome.test");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class, null, pvs);
        annotationConfigApplicationContext.registerBeanDefinition("user",rootBeanDefinition);
        annotationConfigApplicationContext.refresh();
        User user = annotationConfigApplicationContext.getBean(User.class);
        System.out.println(user);
    }
}
