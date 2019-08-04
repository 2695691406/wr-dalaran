package com.qisimanxiang.workreport.dalaran.protocol.http.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
@Slf4j
public class JsonMapper {

    public static JsonMapper NON_EMPTY;
    public static JsonMapper NON_DEFAULT;
    public static JsonMapper NON_NULL;

    private ObjectMapper mapper = new ObjectMapper();

    public JsonMapper(JsonInclude.Include include) {
        this.mapper.setSerializationInclusion(include);
        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.mapper.registerModule(new GuavaModule());
    }

    public String toString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.warn("write to json string error:" + o, e);
            return null;
        }
    }

    public <T> T prase(String s, JavaType javaType) {
        try {
            return mapper.readValue(s, javaType);
        } catch (IOException e) {
            log.warn("read to object error:" + s, e);
            return null;
        }
    }

    public <T> T prase(String s, Class<T> tClass) {
        try {
            return mapper.readValue(s, tClass);
        } catch (IOException e) {
            log.warn("read to object error:" + s, e);
            return null;
        }
    }

    public <T> T prase(byte[] s, Class<T> tClass) {
        try {
            return mapper.readValue(s, tClass);
        } catch (IOException e) {
            log.warn("read to object error:" + s, e);
            return null;
        }
    }

    static {
        NON_EMPTY = new JsonMapper(JsonInclude.Include.NON_EMPTY);
        NON_DEFAULT = new JsonMapper(JsonInclude.Include.NON_DEFAULT);
        NON_NULL = new JsonMapper(JsonInclude.Include.NON_NULL);
    }
}
