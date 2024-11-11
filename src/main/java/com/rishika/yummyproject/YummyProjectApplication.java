package com.rishika.yummyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class YummyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(YummyProjectApplication.class, args);
    }

}
