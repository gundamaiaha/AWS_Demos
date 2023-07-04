package com.myapps.rds.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapps.rds.dto.AWSSecrets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class AppConfig {



    private final ObjectMapper objectMapper;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Bean
    public DataSource dataSource() throws JsonProcessingException {
        AWSSecrets secrets = getSecret();
        return DataSourceBuilder
                .create()
                //.driverClassName("com.")
                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" +
                        secrets.getPort() + "/productsdb")
                .username(secrets.getUsername())
                .password(secrets.getPassword())
                .build();
    }

    private AWSSecrets getSecret() throws JsonProcessingException {

        String secretName = "productsdb-secret";
        Region region = Region.of("ap-south-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsAccessKey,
                                awsSecretKey)
                ))
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
            String secret = getSecretValueResponse.secretString();
            return objectMapper.readValue(secret, AWSSecrets.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }


}
