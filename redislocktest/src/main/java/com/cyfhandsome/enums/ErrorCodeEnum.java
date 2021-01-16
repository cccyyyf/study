package com.cyfhandsome.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author cyf
 * @date 2021/1/14 22:31
 */
public enum ErrorCodeEnum implements ErrorCode {
    /**
     * 自定义异常常量
     */
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;



    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    /**
     * @param code        错误码
     * @param description 描述
     */
    private ErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.desc = description;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static ErrorCodeEnum getByCode(String code) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return NOT_FOUND;
    }

    /**
     * 枚举是否包含此code
     *
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
