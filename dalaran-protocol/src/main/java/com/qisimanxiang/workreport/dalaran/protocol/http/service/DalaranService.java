package com.qisimanxiang.workreport.dalaran.protocol.http.service;

/**
 * 基接口
 * 限制只能是单参，一个接口一个类
 * TODO 是否统一用接口限制单参，此方法的副作用就是，一个接口要写一个类，接口多的情况下类数量会增加
 * @author wangmeng
 * @date 2019-08-04
 */
public interface DalaranService<R, P> {
    R excute(P p);
}
