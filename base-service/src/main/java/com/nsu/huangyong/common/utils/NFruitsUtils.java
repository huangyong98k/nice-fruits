package com.nsu.huangyong.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 水果商城工具类
 * @author Yong Huang
 */
@Slf4j
public class NFruitsUtils {
    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String DEFAULT_TIME_FORMAT = "yyyyMMddHHmmssSSS";

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
     */
    public static String dateToStr(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        } catch (Exception e) {
            log.debug("dateToStr is excepted. " , e);
            return "";
        }
    }

    /**
     * 获取当前时间
     * @return 返回时间精确到秒，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * 生成17的唯一编号
     */
    public static  String identifier(){
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        return (new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(time));
    }

    /**
     * 获取6位的随机数
     * @return
     */
    public static String generateNaturalNumber(){
        Random random = new Random();
        String str = String.valueOf(random.nextInt(999999)+1);
        if (str.length() == 1 ) {
            str = "00000" + str;
        }else if(str.length() == 2){
            str = "0000" + str;
        }else if (str.length() == 3){
            str = "000"+str;
        }else if (str.length() == 4){
            str = "00"+str;
        }else if (str.length() == 5){
            str = "0"+str;
        }
        return str;
    }
}
