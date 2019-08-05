package com.qisimanxiang.workreport.dalaran;

import com.qisimanxiang.workreport.dalaran.protocol.http.DalaranHttpProtocolStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DalaranDemoApplication implements CommandLineRunner {

    @Autowired
    private DalaranHttpProtocolStarter starter;

    public DalaranDemoApplication() {
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DalaranDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        starter.start();
    }
}
