package com.architect.peng.openserver;

import com.architect.peng.openserver.order.IOderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OpenServerApplication {

    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");

        SpringApplication.run(OpenServerApplication.class, args);

    }

}
