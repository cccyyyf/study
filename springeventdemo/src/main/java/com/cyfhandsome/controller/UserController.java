package com.cyfhandsome.controller;

import com.cyfhandsome.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cyf
 * @date 2021/5/24 14:25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/register")
    public void register(String userName, String phone, String email) {
        userService.register(userName, phone, email);
    }
}
