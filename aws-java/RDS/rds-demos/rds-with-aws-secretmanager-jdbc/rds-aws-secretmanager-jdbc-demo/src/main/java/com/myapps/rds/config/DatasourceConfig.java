package com.myapps.rds.config;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {


    @Bean
    public DataSource dataSource() {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode secretsJson = null;
        String secret = null;
       // String secretdata= null;
        Regions region = Regions.AP_SOUTH_1;

        final AWSSecretsManager secretsManager =
                AWSSecretsManagerClientBuilder.standard()
                        .withRegion(region)
                        .build();
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId("dev/empDb/mysql");
                //.withVersionId("AWSCURRENT");
        try {
            GetSecretValueResult secretValue = secretsManager.getSecretValue(getSecretValueRequest);
            if (secretValue.getSecretString() != null) {
                secret = secretValue.getSecretString();
            }
//            } else {
//                secretdata=secretValue.getSecretString();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (secret != null) {
            try {
                secretsJson = objectMapper.readTree(secret);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String host = secretsJson.get("host").textValue();
        String dbname = secretsJson.get("dbname").textValue();


        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName("com.amazonaws.secretsmanager.sql.AWSSecretsManagerMySQLDriver")
                .url("jdbc-secretsmanager:mysql://" + host + ":3306/" + dbname)
                .username("dev/empDb/mysql")
                .build();

        return dataSource;
    }

}
