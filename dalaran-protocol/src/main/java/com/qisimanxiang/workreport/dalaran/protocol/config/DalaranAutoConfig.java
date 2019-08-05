package com.qisimanxiang.workreport.dalaran.protocol.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自启动类
 * @author wangmeng
 * @date 2019-08-05
 */
@ConditionalOnProperty(name = "dalaran.enable",havingValue = "true")
@Component
@EnableConfigurationProperties(DalaranConfig.class)
public class DalaranAutoConfig {

}
