package com.cyfhandsome.service;

import com.cyfhandsome.domain.Member;
import com.cyfhandsome.handler.MemberAuthHandler;
import com.cyfhandsome.handler.MemberBaseHandler;
import com.cyfhandsome.handler.MemberLoginHandler;
import com.cyfhandsome.handler.MemberValidateHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author cyf
 * @date 2021/11/23 10:26
 * 责任链模式优化后
 */
public class UpdateMemberService {
    public static void main(String[] args) {
        UpdateMemberService service = new UpdateMemberService();
        service.login("tom", "666");
    }

    public void login(String loginName, String loginPass) {
        /*MemberBaseHandler validateHandler = new MemberValidateHandler();
        MemberBaseHandler loginHandler = new MemberLoginHandler();
        MemberBaseHandler authHandler = new MemberAuthHandler();
        validateHandler.next(loginHandler);
        loginHandler.next(authHandler);
        validateHandler.doHandler(new Member(loginName, loginPass));*/
        MemberBaseHandler.Builder builder = new MemberBaseHandler.Builder();
        builder.addHandler(new MemberValidateHandler())
                .addHandler(new MemberLoginHandler())
                .addHandler(new MemberAuthHandler());
        builder.build().doHandler(new Member(loginName,loginPass));
    }
}
