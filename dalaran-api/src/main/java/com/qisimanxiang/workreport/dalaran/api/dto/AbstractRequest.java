package com.qisimanxiang.workreport.dalaran.api.dto;

import java.io.Serializable;

/**
 * 抽象RPC请求对象
 * @author wangmeng
 * @date 2019-08-06
 */
public abstract class AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8115823591900223928L;

    /**
     * 是否为读操作
     * @return 是否
     */
    public abstract boolean isWrite();
}
