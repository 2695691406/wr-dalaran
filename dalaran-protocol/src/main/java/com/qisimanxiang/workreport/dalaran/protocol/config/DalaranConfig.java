package com.qisimanxiang.workreport.dalaran.protocol.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @author wangmeng
 * @date 2019-08-05
 */
@ConfigurationProperties(
        prefix = "dalaran",
        ignoreUnknownFields = true
)
@Data
public class DalaranConfig {
    private HttpConfig http;


    @Data
    public static class HttpConfig implements Serializable {
        private static final long serialVersionUID = 5759066465018627904L;
        private Integer port;
        private Integer maxContent;
    }

    @PostConstruct
    void init() {
        if (http == null) {
            http = new HttpConfig();
            http.port = 8080;
            http.maxContent = 512 * 1024;
        }
    }
}
