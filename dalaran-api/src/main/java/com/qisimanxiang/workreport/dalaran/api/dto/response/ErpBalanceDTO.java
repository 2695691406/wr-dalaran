package com.qisimanxiang.workreport.dalaran.api.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 余额RPC传输对象
 *
 * @author wangmeng
 * @date 2019-08-06
 */
@Data
public class ErpBalanceDTO implements Serializable {
    private static final long serialVersionUID = -6682656264972818378L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 余额
     */
    private Long balance;
}
