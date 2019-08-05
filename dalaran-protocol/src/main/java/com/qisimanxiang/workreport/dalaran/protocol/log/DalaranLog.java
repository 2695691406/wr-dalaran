package com.qisimanxiang.workreport.dalaran.protocol.log;

import lombok.Data;

/**
 * @author wangmeng
 * @date 2019-08-05
 */
@Data
public class DalaranLog {
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 协议
     * (此处依旧可拓展，比如http协议是否要把头信息也记录下来？dubbo是否把IP或上下文记录下来？)
     */
    private String protocol;
    /**
     * 方法信息
     */
    private String methodInfo;
    /**
     * 参数
     */
    private Object parameters;
    /**
     * 调用结果
     */
    private Object result;
    /**
     * 异常信息
     */
    private Exception e;

    

}
