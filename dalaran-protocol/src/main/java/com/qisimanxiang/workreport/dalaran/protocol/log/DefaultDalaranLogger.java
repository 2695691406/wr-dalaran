package com.qisimanxiang.workreport.dalaran.protocol.log;

import com.qisimanxiang.workreport.dalaran.protocol.http.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author wangmeng
 * @date 2019-08-05
 */
@ConditionalOnMissingBean(DalaranLogger.class)
@Component
@Slf4j
public class DefaultDalaranLogger implements DalaranLogger {
    @Override
    public void log(DalaranLog dalaranLog) {
        log.info("{}请求 ，方法: {} , 参数: {} , 响应结果: {}",
                dalaranLog.getProtocol(),
                dalaranLog.getMethodInfo(),
                JsonMapper.NON_DEFAULT.toJson(dalaranLog.getParameters()),
                JsonMapper.NON_DEFAULT.toJson(dalaranLog.getResult()));
    }
}
