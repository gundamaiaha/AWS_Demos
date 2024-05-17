package com.example.quizmasterservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

@Service
public class ParameterStoreService {

    private  SsmClient ssmClient;


    public ParameterStoreService(@Value("${aws.region}") String region,
                                 @Value("${aws.accessKey}") String accessKey,
                                 @Value("${aws.secretAccessKey}") String secretKey) {
        this.ssmClient = SsmClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    public String getParameter(String parameterName) {
        return ssmClient
                .getParameter(request -> request.name(parameterName).withDecryption(true))
                .parameter().value();
    }


}
