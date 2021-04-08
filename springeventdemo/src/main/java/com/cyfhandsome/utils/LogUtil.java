package com.cyfhandsome.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.Date;

/**
 * 统一日志工具：便于后期日志统一管理
 *
 * @author yuqili
 */
public class LogUtil {
    private LogUtil() {
    }

    /**
     * debug级别日志打印
     *
     * @param logInfo
     * @param logger
     * @param contents
     */
    public static void debug(Logger logger, String logInfo, Object... contents) {
        if (logger.isDebugEnabled()) {
            if (contents != null && contents.length > 0) {
                convertContents(contents);
            }
            String text = MessageFormat.format(logInfo, contents);
            logger.debug(text);
        }
    }

    /**
     * info级别日志打印
     *
     * @param logInfo
     * @param logger
     * @param contents
     */
    public static void info(Logger logger, String logInfo, Object... contents) {
        if (logger.isInfoEnabled()) {
            if (contents != null && contents.length > 0) {
                convertContents(contents);
            }

            String text = MessageFormat.format(logInfo, contents);
            logger.info(text);
        }
    }

    /**
     * warn级别打印
     *
     * @param logInfo
     * @param logger
     * @param contents
     */
    public static void warn(Logger logger, String logInfo, Object... contents) {
        if (logger.isWarnEnabled()) {
            if (contents != null && contents.length > 0) {
                convertContents(contents);
            }
            String text = MessageFormat.format(logInfo, contents);
            logger.warn(text);
        }
    }

    /**
     * 打印error级别日志
     *
     * @param logInfo
     * @param logger
     * @param contents
     */
    public static void error(Logger logger, String logInfo, Object... contents) {
        if (logger.isErrorEnabled()) {
            if (contents != null && contents.length > 0) {
                convertContents(contents);
            }
            String text = MessageFormat.format(logInfo, contents);
            logger.error(text);
        }
    }

    /**
     * 异常日志打印，包含抛出异常信息
     *
     * @param logInfo
     * @param e
     * @param logger
     * @param contents
     */
    public static void errorContainsThrowable(Logger logger, String logInfo, Throwable e, Object... contents) {
        if (logger.isErrorEnabled()) {
            if (contents != null && contents.length > 0) {
                convertContents(contents);
            }
            String text = MessageFormat.format(logInfo, contents);
            logger.error(text, e);
        }
    }

    /**
     * 将需要打印的信息进行转换，如对象转换成json字符串，时间转换成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param contents
     */
    private static void convertContents(Object... contents) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == null) {
                contents[i] = "";
            }

            if (contents[i] instanceof Date) {
                contents[i] = DateFormatUtils.format(((Date) contents[i]), "yyyy-MM-dd HH:mm:ss");
            } else if (contents[i] != null) {
                contents[i] = JSON.toJSONString(contents[i]);
            } else {
                contents[i] = JSON.toJSONString(String.valueOf(contents[i]));
            }
        }
    }

}
