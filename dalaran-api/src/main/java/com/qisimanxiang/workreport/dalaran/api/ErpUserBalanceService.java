package com.qisimanxiang.workreport.dalaran.api;

import com.qisimanxiang.workreport.dalaran.api.dto.response.ErpBalanceDTO;
import com.qisimanxiang.workreport.dalaran.api.dto.request.ErpBalanceQueryRequest;

/**
 * 示例ERP余额服务
 *
 * @author wangmeng
 * @date 2019-08-06
 */
public interface ErpUserBalanceService {
    /**
     * 查询用户ERP余额
     *
     * @param request   查询数据
     * @return          余额数据
     */
    ErpBalanceDTO query(ErpBalanceQueryRequest request);

}
