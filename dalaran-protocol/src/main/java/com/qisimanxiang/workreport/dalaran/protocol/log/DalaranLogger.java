package com.qisimanxiang.workreport.dalaran.protocol.log;

/**
 * 日志记录器
 * 有两种方式
 * 1. 只允许使用方实现存储,日志是直接打印，还是落DB，亦或者推到MQ。
 * 2. 让使用方高度自定义（从使用便利上来讲，我们优先不考虑这种）
 *
 * @author wangmeng
 * @date 2019-08-05
 */
public interface DalaranLogger {
    void log(DalaranLog log);
}
