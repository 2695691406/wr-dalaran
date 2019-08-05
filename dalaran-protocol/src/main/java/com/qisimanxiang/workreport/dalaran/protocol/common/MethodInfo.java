package com.qisimanxiang.workreport.dalaran.protocol.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * 方法信息类
 * @author wangmeng
 */
@Getter
@AllArgsConstructor
public class MethodInfo {
    private Method method;
    private Object bean;
}