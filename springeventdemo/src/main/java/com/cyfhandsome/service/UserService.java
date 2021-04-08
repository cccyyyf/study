package com.cyfhandsome.service;

import com.cyfhandsome.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;


/**
 * @author cyf
 * @date 2021/4/8 16:48
 */
@Service
public class UserService implements ApplicationEventPublisherAware {

    private static final Logger log  = LoggerFactory.getLogger(UserService.class);

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String userName,String phone,String email){
        //注册
        log.info("{} 注册成会员",userName);

        //发布服务
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,userName,phone,email));
    }

}
