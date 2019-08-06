package com.qisimanxiang.workreport.dalaran.api.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangmeng
 * @date 2019-08-06
 */
@Getter
@AllArgsConstructor
public enum DalaranResponseCode implements ResponseCode {
    /**
     * 用户不存在的错误代码
     */
    USER_NOT_EXIST(500, "user.not.exist");

    private int code;
    private String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
