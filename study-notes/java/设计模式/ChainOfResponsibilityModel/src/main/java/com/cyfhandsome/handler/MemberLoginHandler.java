package com.cyfhandsome.handler;

import com.cyfhandsome.domain.Member;

/**
 * @author cyf
 * @date 2021/11/23 10:36
 */
public class MemberLoginHandler extends MemberBaseHandler{
    @Override
    public void doHandler(Member member) {
        System.out.println("登录成功！");
        member.setRoleName("管理员");
        nextHandler.doHandler(member);
    }
}
