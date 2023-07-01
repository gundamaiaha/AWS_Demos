package com.myapps.productsconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@EnableSqs
@SpringBootApplication
public class ProductsConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductsConsumerApplication.class, args);
	}

}
