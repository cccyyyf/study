package com.cyfhandsome.enums;

/**
 * @author cyf
 * @date 2021/1/14 22:29
 */
public interface ErrorCode {
    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getDesc();
}
