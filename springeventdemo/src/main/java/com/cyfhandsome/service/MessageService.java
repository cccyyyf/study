package com.cyfhandsome.service;

import com.cyfhandsome.event.UserRegisterEvent;
import com.cyfhandsome.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author cyf
 * @date 2021/4/8 17:07
 */
@Service
public class MessageService implements ApplicationListener<UserRegisterEvent> {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    /**
     * 添加async注解为了异步处理
     * @param userRegisterEvent
     */
    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        //发送邮件逻辑
        LogUtil.info(log,"给【{0}】发送短信,手机号是{1}",userRegisterEvent.getUserName(),userRegisterEvent.getPhone());
    }
}
