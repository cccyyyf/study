package com.cyfhandsome.domain;

import lombok.Data;

/**
 * @author cyf
 * @date 2021/11/23 10:25
 */
@Data
public class Member {
    private String userName;

    private String password;

    private String roleName;

    public Member(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
