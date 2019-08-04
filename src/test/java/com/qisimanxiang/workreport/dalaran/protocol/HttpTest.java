package com.qisimanxiang.workreport.dalaran.protocol;

import com.qisimanxiang.workreport.dalaran.protocol.http.HttpProtocolStarter;

/**
 * @author wangmeng
 * @date 2019-08-04
 */
public class HttpTest {
    public static void main(String[] args) throws InterruptedException {
        HttpProtocolStarter starter=new HttpProtocolStarter(9099);
        starter.start();
    }
}
