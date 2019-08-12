package com.qisimanxiang.workreport.dalaran.protocol.starter;

import com.qisimanxiang.workreport.dalaran.protocol.config.DalaranConfig;
import com.qisimanxiang.workreport.dalaran.protocol.http.DalaranHttpProtocolStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @author wangmeng
 * @date 2019-08-12
 */
@ConditionalOnBean({DalaranConfig.class})
@Component
@Slf4j
public class DalaranStarter implements CommandLineRunner {
    @Autowired
    private DalaranHttpProtocolStarter starter;

    @Override
    public void run(String... args) throws Exception {
        log.info("dalaran running ...");
        starter.start();

    }
}
