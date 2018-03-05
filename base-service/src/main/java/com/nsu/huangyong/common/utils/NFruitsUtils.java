package com.nsu.huangyong.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 水果商城工具类
 * @author Yong Huang
 */
@Slf4j
public class NFruitsUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";

    private NFruitsUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 串是否包含字符
     * @param str 待检验串
     * @return 参数串不为空且串长不为0返回true，空或者零长度串参数返回false
     */
    public static boolean isContainCharacterStr(String str) {
        return (str != null && str.trim().length() > 0);
    }

    /**
     * 串是否不包含字符
     * @param str 待检验串
     * @return 参数串isContainCharacterStr方法返回值取反
     */
    public static boolean isNotContainCharacterStr(String str) {
        return !isContainCharacterStr(str);
    }

    /**
     * 生成参数错误消息
     *
     * 主要用来生成对外API传入的参数的错误消息
     * @param errorList 参数校验错误信息列表
     * @return 错误消息
     */
    public static String createInvalidParameterMessage(List<FieldError> errorList) {
        StringBuilder sb = new StringBuilder();
        for (FieldError error : errorList) {
            sb.append(error.getField()).append(error.getDefaultMessage()).append(";");
        }

        return sb.toString();
    }

    /**
     * 将指定时间的Date部分转成yyyyMMdd格式的Integer
     * @param localDateTime 要转换的时间
     * @return yyyyMMdd格式的Integer值
     */
    public static Integer dateToInteger(LocalDateTime localDateTime) {
        try {
            return Integer.valueOf(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        } catch (Exception e) {
            log.debug("dateToInteger is excepted. ", e);
            return 0;
        }
    }

    /**
     * 将指定时间的Date部分转成默认格式的Integer
     * @param localDateTime 要转换的时间
     * @return 默认格式的String
     */
    public static String dateToStr(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        } catch (Exception e) {
            log.debug("dateToStr is excepted. " , e);
            return "";
        }
    }
}
