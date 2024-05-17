package com.example.quizmasterservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {
    private final ParameterStoreService parameterStoreService;


    @Value("${aws.ssm.db.url-parameter}")
    private String dbUrlParameter;

    @Value("${aws.ssm.db.username-parameter}")
    private String dbUsernameParameter;

    @Value("${aws.ssm.db.password-parameter}")
    private String dbPasswordParameter;

    @Bean
    @DependsOn("parameterStoreService")
    public DataSource dataSource() {

        String url = parameterStoreService.getParameter(dbUrlParameter);
        String username = parameterStoreService.getParameter(dbUsernameParameter);
        String password = parameterStoreService.getParameter(dbPasswordParameter);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }


}
