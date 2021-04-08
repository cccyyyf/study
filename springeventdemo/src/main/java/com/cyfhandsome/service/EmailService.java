package com.cyfhandsome.service;

import com.cyfhandsome.event.UserRegisterEvent;
import com.cyfhandsome.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author cyf
 * @date 2021/4/8 17:10
 */
@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @EventListener
    public void setEmail(UserRegisterEvent userRegisterEvent){
        LogUtil.info(log,"给【{0}】发送邮件,邮箱是{1}",userRegisterEvent.getUserName(),userRegisterEvent.getEmail());
    }

}
