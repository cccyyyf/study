package com.cyfhandsome.advice;

import com.cyfhandsome.enums.ErrorCodeEnum;
import com.cyfhandsome.exception.ErrorException;
import com.cyfhandsome.modol.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cyf
 * @date 2021/1/14 22:57
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public RestResult ErrorExceptionHandler(HttpServletRequest req, ErrorException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return RestResult.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public RestResult exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return RestResult.error(ErrorCodeEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return RestResult.error(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }
}

