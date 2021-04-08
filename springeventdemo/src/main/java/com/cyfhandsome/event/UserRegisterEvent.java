package com.cyfhandsome.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author cyf
 * @date 2021/4/8 15:39
 */
public class UserRegisterEvent extends ApplicationEvent {

    private String userName;

    private String phone;

    private String email;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source,String userName,String phone,String email) {
        super(source);
        this.userName = userName;
        this.phone = phone;
        this.email = email;
    }


    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
