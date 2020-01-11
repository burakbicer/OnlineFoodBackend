package com.bilgeadam.onlinefoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OnlinefoodappApplication {
    private static String[] args;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(OnlinefoodappApplication.class, args);
    }
}
