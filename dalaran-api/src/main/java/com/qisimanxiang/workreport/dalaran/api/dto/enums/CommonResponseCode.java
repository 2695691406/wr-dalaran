package com.qisimanxiang.workreport.dalaran.api.dto.enums;

import lombok.Getter;

/**
 * 通用错误代码
 *
 * @author wangmeng
 * @date 2019-08-06
 */
@Getter
public enum CommonResponseCode implements ResponseCode {
    /**
     * 统一成功的错误代码
     */
    SUCCESS(200, "success"),
    /**
     * 统一失败的错误代码
     */
    FAILED(500, "faild");
    private int code;
    private String desc;

    CommonResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
