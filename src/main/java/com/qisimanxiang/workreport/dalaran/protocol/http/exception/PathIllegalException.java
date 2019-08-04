package com.qisimanxiang.workreport.dalaran.protocol.http.exception;

/**
 * 异常-路径非法
 *
 * @author wangmeng
 * @date 2019-08-04
 */
public class PathIllegalException extends RuntimeException {

    private static final long serialVersionUID = 3927087254796874259L;

    public PathIllegalException(String message) {
        super(message);
    }

    public PathIllegalException() {
    }
}
