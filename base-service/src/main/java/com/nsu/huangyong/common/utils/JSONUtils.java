package com.nsu.huangyong.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.nsu.huangyong.exception.DataFormatException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JSON序列化工具类
 * @author Yong Huang
 */
@Slf4j
public class JSONUtils {
    private static ObjectMapper objectMapper;
    private static final String DEFAULT_DATETIME_FORMAT = "yyyyMMddHHmmss";

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
                DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
                DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT)));

        objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .registerModule(javaTimeModule)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_FORMAT));
    }

    private JSONUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 转换成JSON串
     * @param obj 待转换对象
     * @return JSON串
     */
    public static String toJson(Object obj) throws DataFormatException {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new DataFormatException(e);
        }
    }

    /**
     * 转换成JSON串，当转换过程中发生异常，这返回空字符串
     * @param obj 待转换对象
     * @return JSON串
     */
    public static String toJsonAndIgnoreException(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ignored) {
            log.debug("Object to Json is excepted. ", ignored);
            return "";
        }
    }

    /**
     * 转换成对象（泛型方法）
     * @param jsonStr JSON串
     * @param clazz 待转换对象class
     * @param <T> 待转换对象类型
     * @return 对象
     */
    public static <T> T fromJson(final String jsonStr, Class<T> clazz) throws DataFormatException {
        if (NFruitsUtils.isNotContainCharacterStr(jsonStr)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new DataFormatException(e);
        }
    }
}