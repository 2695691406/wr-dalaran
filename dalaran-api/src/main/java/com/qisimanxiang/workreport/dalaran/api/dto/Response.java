package com.qisimanxiang.workreport.dalaran.api.dto;

import com.qisimanxiang.workreport.dalaran.api.dto.enums.CommonResponseCode;
import com.qisimanxiang.workreport.dalaran.api.dto.enums.ResponseCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * 一个是标记，对方是否出现了未知异常。
 * 一个是标记，对方返回何种业务异常。
 * 具体如何使用，对接双方可以自己考量，一般来讲，在两种都见过，用success的比较多。
 * 对于复杂的业务接口，调用方如果需要感知多种异常情况，推荐使用code。
 * 对于简单的接口，往往只有成功和失败，调用方无需为不同的业务失败情况做细化操作时，可以使用success。
 *
 * @author wangmeng
 * @date 2019-08-06
 */
@Getter
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -6017076007061197785L;
    /**
     * 请求成功的判断标识
     */
    private boolean success;
    /**
     * 业务异常代码
     */
    private int code;
    /**
     * 业务异常代码描述信息
     */
    private String message;
    /**
     * 数据对象
     */
    private T data;
    /**
     * 拓展对象
     */
    private Map<String, String> extra;

    private Response() {
    }

    /**
     * 成功响应
     *
     * @return
     */
    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.success = true;
        response.code = CommonResponseCode.SUCCESS.getCode();
        response.message = CommonResponseCode.SUCCESS.getDesc();
        return response;
    }

    /**
     * 成功响应
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T t, ResponseCode code) {
        Response<T> response = new Response<>();
        response.data = t;
        response.success = true;
        response.code = code.getCode();
        response.message = code.getDesc();
        return response;
    }

    /**
     * 成功响应
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T t) {
        Response<T> response = new Response<>();
        response.data = t;
        response.success = true;
        response.code = CommonResponseCode.SUCCESS.getCode();
        response.message = CommonResponseCode.SUCCESS.getDesc();
        return response;
    }

    /**
     * 失败响应
     *
     * @param code
     * @return
     */
    public static Response filed(CommonResponseCode code) {
        Response response = new Response<>();
        response.success = false;
        response.code = code.getCode();
        response.message = code.getDesc();
        return response;
    }

    /**
     * 失败响应
     *
     * @return
     */
    public static Response filed() {
        Response response = new Response<>();
        response.success = false;
        response.code = CommonResponseCode.FAILED.getCode();
        response.message = CommonResponseCode.FAILED.getDesc();
        return response;
    }

}
