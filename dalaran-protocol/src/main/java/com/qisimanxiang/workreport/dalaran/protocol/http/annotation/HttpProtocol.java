package com.qisimanxiang.workreport.dalaran.protocol.http.annotation;

import java.lang.annotation.*;

/**
 * @author wangmeng
 * @date 2019-08-04
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpProtocol {
    /**
     * http路径
     *
     * @return
     */
    String path();
}
