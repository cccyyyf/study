package com.cyfhandsome.test.config;

import com.cyfhandsome.test.domain.Person;
import com.cyfhandsome.test.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyf
 * @date 2021/11/24 16:03
 */
public class GenericBeanDefinitionConfigTest {
    /**
     * 现在 Spring Boot 广泛流行之后，Java 配置使用越来越多，以 @Configuration 注解标记配置类会被解析为 AnnotatedGenericBeanDefinition；
     * 以 @Bean 注解标记的 Bean 会被解析为 ConfigurationClassBeanDefinition。
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("username","test-cyf");
        propertyValues.add("address","com.cyfhandsome.test");
        GenericBeanDefinition rootBeanDefinition = new GenericBeanDefinition();
        rootBeanDefinition.setBeanClass(User.class);
        rootBeanDefinition.setPropertyValues(propertyValues);
        annotationConfigApplicationContext.registerBeanDefinition("user",rootBeanDefinition);
        GenericBeanDefinition childBean = new GenericBeanDefinition();
        childBean.setParentName("user");
        childBean.setBeanClass(Person.class);
        childBean.getPropertyValues().add("nickname","cyf_child_test");
        annotationConfigApplicationContext.registerBeanDefinition("person",childBean);
        annotationConfigApplicationContext.refresh();
        System.out.println(annotationConfigApplicationContext.getBean(User.class));
        System.out.println(annotationConfigApplicationContext.getBean(Person.class));

    }
}
