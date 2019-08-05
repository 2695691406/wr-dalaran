package com.qisimanxiang.workreport.dalaran.protocol.http.exception;

/**
 * 异常-方法参数非法
 *
 * @author wangmeng
 * @date 2019-08-04
 */
public class MethodParameterIllegalException extends RuntimeException {

    private static final long serialVersionUID = 7055890918020577320L;

    public MethodParameterIllegalException(String message) {
        super(message);
    }
}
