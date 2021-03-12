package com.cyfhandsome.exception;

import com.cyfhandsome.enums.ErrorCode;
import lombok.Data;

/**
 * @author cyf
 * @date 2021/1/14 22:25
 */
@Data
public class ErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public ErrorException() {
        super();
    }

    public ErrorException(ErrorCode errorInfoInterface) {
        super(errorInfoInterface.getCode());
        this.errorCode = errorInfoInterface.getCode();
        this.errorMsg = errorInfoInterface.getDesc();
    }

    public ErrorException(ErrorCode errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getCode(), cause);
        this.errorCode = errorInfoInterface.getCode();
        this.errorMsg = errorInfoInterface.getDesc();
    }

    public ErrorException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ErrorException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
