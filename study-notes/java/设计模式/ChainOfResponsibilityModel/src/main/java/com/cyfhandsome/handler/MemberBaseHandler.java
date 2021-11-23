package com.cyfhandsome.handler;

import com.cyfhandsome.domain.Member;

/**
 * @author cyf
 * @date 2021/11/23 10:31
 */
public abstract class MemberBaseHandler<T> {
    protected MemberBaseHandler nextHandler;

    private void next(MemberBaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler(Member member);

    //结合建造者模式构建责任链模式
    public static class Builder<T>{
        private MemberBaseHandler<T> head;
        private MemberBaseHandler<T> tail;

        public Builder<T> addHandler(MemberBaseHandler handler){
            // do {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
            // }while (false);//真正框架中，如果是双向链表，会判断是否已经到了尾部
            return this;
        }

        public MemberBaseHandler<T> build(){
            return this.head;
        }
    }
}
