package com.cyfhandsome.handler;

import com.cyfhandsome.domain.Member;

/**
 * @author cyf
 * @date 2021/11/23 10:39
 */
public class MemberAuthHandler extends MemberBaseHandler{
    @Override
    public void doHandler(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员，没有操作权限");
            return;
        }
        System.out.println("允许操作");
    }
}
