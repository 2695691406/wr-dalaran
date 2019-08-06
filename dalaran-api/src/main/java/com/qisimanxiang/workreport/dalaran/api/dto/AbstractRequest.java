package com.qisimanxiang.workreport.dalaran.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 抽象RPC请求对象
 *
 * @author wangmeng
 * @date 2019-08-06
 */
public abstract class AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8115823591900223928L;

    /**
     * 追踪ID
     * 当然RPC上线文也可以传此对象
     */
    @Getter
    @Setter
    private String traceId;

    /**
     * 是否为读操作
     *
     * @return 是否
     */
    public abstract boolean isWrite();
}
