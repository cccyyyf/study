package com.cyfhandsome.factory;

/**
 * @author cyf
 * @date 2021/11/25 13:42
 */
public class BeanException extends RuntimeException {
    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
