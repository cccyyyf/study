package com.cyfhandsome.test.config;

import com.cyfhandsome.test.domain.Person;
import com.cyfhandsome.test.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyf
 * @date 2021/11/24 15:55
 */
public class ChildBeanDefinitionConfigTest {
    public static void main(String[] args) {
        //ChildBeanDefinition具有从父级bean集成数据的能力
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("username","test-cyf");
        propertyValues.add("address","com.cyfhandsome.test");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class,null,propertyValues);
        annotationConfigApplicationContext.registerBeanDefinition("user",rootBeanDefinition);
        ChildBeanDefinition user = new ChildBeanDefinition("user");
        user.setBeanClass(Person.class);
        user.getPropertyValues().add("nickname","cyf_test_child");
        annotationConfigApplicationContext.registerBeanDefinition("person",user);
        annotationConfigApplicationContext.refresh();
        System.out.println(annotationConfigApplicationContext.getBean(User.class));
        System.out.println(annotationConfigApplicationContext.getBean(Person.class));

    }

}
