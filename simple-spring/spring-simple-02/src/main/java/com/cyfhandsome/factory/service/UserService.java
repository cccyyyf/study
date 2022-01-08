package com.cyfhandsome.factory.service;

/**
 * @author cyf
 * @date 2021/11/24 17:00
 */
public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void getUserInfo() {
        System.out.println("查询用户信息");
    }
}
