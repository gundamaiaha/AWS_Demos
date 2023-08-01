package com.example.rdsdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {


    @Value("${AWS_RDS_MYSQL_URL}")
    private String awsConnectionUrl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("awsConnectionUrl = " + awsConnectionUrl);

    }
}
