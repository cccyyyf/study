package com.cyfhandsome.factory.demo.handler;

/**
 * @author cyf
 * @date 2021/11/26 11:24
 */
public interface BaseNodeType<T> {
    String nodeType();

    BaseNodeHandler<T> getCorrespondingClass();
}
