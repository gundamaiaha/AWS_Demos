package com.example.quizmasterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class QuizMasterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizMasterServiceApplication.class, args);
    }

}
