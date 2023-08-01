package com.example.rds.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class RDSDataSourceConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${rds.db.endpoint}")
    private String rdsDbEndpoint;

    @Value("${rds.db.username}")
    private String rdsDbUsername;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + rdsDbEndpoint + ":3306" + "/emp_db");
        dataSource.setUsername(rdsDbUsername);
       // dataSource.setPassword(generateAuthToken());
        return dataSource;
    }


    private String generateAuthToken() {
        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
                .credentials(new DefaultAWSCredentialsProviderChain())
                .region(awsRegion)
                .build();

        final String authToken = generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(rdsDbEndpoint)
                        .port(3306)
                        .userName(rdsDbUsername)
                        .build());
        return authToken;
    }


}
