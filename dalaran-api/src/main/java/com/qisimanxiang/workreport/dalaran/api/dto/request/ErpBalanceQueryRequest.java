package com.qisimanxiang.workreport.dalaran.api.dto.request;

import com.qisimanxiang.workreport.dalaran.api.dto.AbstractRequest;
import lombok.Data;

/**
 * 余额查询对象
 *
 * @author wangmeng
 * @date 2019-08-06
 */
@Data
public class ErpBalanceQueryRequest extends AbstractRequest {
    private static final long serialVersionUID = 1353110950553994795L;
    /**
     * 用户ID
     */
    private Long userId;

    @Override
    public boolean isWrite() {
        return false;
    }
}
