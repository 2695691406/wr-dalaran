package com.qisimanxiang.workreport.dalaran.server.dubbo.impl;

import com.qisimanxiang.workreport.dalaran.api.ErpUserBalanceService;
import com.qisimanxiang.workreport.dalaran.api.dto.request.ErpBalanceQueryRequest;
import com.qisimanxiang.workreport.dalaran.api.dto.response.ErpBalanceDTO;
import org.springframework.stereotype.Service;

/**
 * @author wangmeng
 * @date 2019-08-06
 */
@Service
public class ErpUserBalanceServiceImpl implements ErpUserBalanceService {

    @Override
    public ErpBalanceDTO query(ErpBalanceQueryRequest request) {
        //TODO 调用CSB
        return null;
    }
}
