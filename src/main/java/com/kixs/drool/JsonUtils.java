/*
 * Copyright (c) 2019.
 * hnf Co.Ltd. 2002-
 * All rights resolved
 */
package com.kixs.drool;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * JSON 操作工具类
 *
 * @author wangbing
 * @version v1.0.0
 * @date 2019/10/19 15:04
 */
@Slf4j
public class JsonUtils {

    /**
     * JSON字符串转换为对象
     *
     * @param json    JSON字符串
     * @param typeRef 类型
     * @param <T>     泛型
     * @return 对象
     */
    public static <T> T jsonToObject( String json, TypeReference<T> typeRef) {
        T result;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            result = objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            log.error("JSON反序列化异常，str={}", json, e);
            throw new RuntimeException("数据格式错误");
        }

        return result;
    }

    /**
     * 对象转换为JSON字符串
     *
     * @param obj 带转换对象
     * @return JSON字符串
     */
    public static String objectToJson(Object obj) {
        String result;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("JSON序列化异常，obj={}", obj, e);
            throw new RuntimeException("数据格式转换失败");
        }

        return result;
    }

    /**
     * 对象转换为JSON字符串(不忽略null)
     *
     * @param obj 带转换对象
     * @return JSON字符串
     */
    public static String objectToJsonNull(Object obj) {
        String result;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("JSON序列化异常，obj={}", obj, e);
            throw new RuntimeException("数据格式转换失败");
        }

        return result;
    }

    /**
     * 对象转换为JSON字符串（全属性字段输出）
     *
     * @param obj 带转换对象
     * @return JSON字符串
     */
    public static String objectToJsonWithNull(Object obj) {
        String result;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("JSON序列化异常，obj={}", obj, e);
            throw new RuntimeException("数据格式转换失败");
        }

        return result;
    }

    public static JsonNode parseNode(String text) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 设置输出时包含属性的风格
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            return objectMapper.readTree(text);
        } catch (IOException e) {
            log.warn("parse json error:" + text, e);
            return null;
        }
    }
}
