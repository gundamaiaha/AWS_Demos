package com.myapps.rds;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class RdsAwsSecretmanagerJdbcDemoApplicationTests {

    @Test
    void contextLoads() {
        try (MockedStatic<SpringApplication> springApplicationMockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            ConfigurableApplicationContext mockApplicationContext = Mockito.mock(ConfigurableApplicationContext.class);
            springApplicationMockedStatic.when(() -> SpringApplication.run(eq(RdsAwsSecretmanagerJdbcDemoApplication.class), any(String[].class)))
                    .thenReturn(mockApplicationContext);
            RdsAwsSecretmanagerJdbcDemoApplication.main(new String[]{});

        }

    }

}
