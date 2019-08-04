package com.qisimanxiang.workreport.dalaran.protocol.http.service;

/**
 * 基接口
 * 限制只能是单参，一个接口一个类
 *
 * @author wangmeng
 * @date 2019-08-04
 */
public interface DalaranService<R, P> {
    R excute(P p);
}
