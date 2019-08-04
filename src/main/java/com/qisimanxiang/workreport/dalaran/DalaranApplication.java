package com.qisimanxiang.workreport.dalaran;

import com.qisimanxiang.workreport.dalaran.protocol.http.HttpProtocolStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DalaranApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DalaranApplication.class, args);
        HttpProtocolStarter starter = new HttpProtocolStarter(8080);
        starter.start();
        Thread.yield();
    }

}
