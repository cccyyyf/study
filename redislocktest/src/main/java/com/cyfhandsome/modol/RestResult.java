package com.cyfhandsome.modol;

import com.alibaba.fastjson.JSONObject;
import com.cyfhandsome.enums.ErrorCode;
import com.cyfhandsome.enums.ErrorCodeEnum;

/**
 * @author cyf
 * @date 2021/1/14 23:04
 */
public class RestResult {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public RestResult() {
    }

    public RestResult(ErrorCode errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static RestResult success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static RestResult success(Object data) {
        RestResult rb = new RestResult();
        rb.setCode(ErrorCodeEnum.SUCCESS.getCode());
        rb.setMessage(ErrorCodeEnum.SUCCESS.getDesc());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static RestResult error(ErrorCode errorInfo) {
        RestResult rb = new RestResult();
        rb.setCode(errorInfo.getCode());
        rb.setMessage(errorInfo.getDesc());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static RestResult error(String code, String message) {
        RestResult rb = new RestResult();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static RestResult error( String message) {
        RestResult rb = new RestResult();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
