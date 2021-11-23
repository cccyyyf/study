package com.cyfhandsome.handler;

import com.cyfhandsome.domain.Member;
import org.apache.commons.lang3.StringUtils;

/**
 * @author cyf
 * @date 2021/11/23 10:34
 */
public class MemberValidateHandler extends MemberBaseHandler {


    @Override
    public void doHandler(Member member) {
        if (StringUtils.isEmpty(member.getUserName()) ||
                StringUtils.isEmpty(member.getPassword())) {
            System.out.println("用户名和密码为空");
            return;
        }
        System.out.println("用户名和密码不为空，可以往下执行");

        nextHandler.doHandler(member);
    }
}
