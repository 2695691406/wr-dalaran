package com.qisimanxiang.workreport.dalaran.protocol.http.mapping;

import com.qisimanxiang.workreport.dalaran.protocol.common.MethodInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019-08-05
 */
public class HttpPathMapping {
    public static ConcurrentHashMap<String, MethodInfo> mapping = new ConcurrentHashMap<>();
}
