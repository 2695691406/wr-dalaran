package com.qisimanxiang.workreport.dalaran.protocol.http.exception;

/**
 * 异常路径已经存在
 *
 * @author wangmeng
 * @date 2019-08-04
 */
public class PathAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 5920198123857287781L;

    public PathAlreadyExistException(String message) {
        super(message);
    }
}
