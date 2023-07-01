package com.myapps.productsconsumer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapps.productsconsumer.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsConsumerService {

    private final AmazonS3 amazonS3;
    private final ObjectMapper objectMapper;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    AmazonSQSAsync amazonSQSAsync;

    @SqsListener("my-queue")
    public void consumeProductsData(final String s3ObjectKey) throws IOException {
        log.info("consuming the products data from : {}",s3ObjectKey);
        S3Object s3Object = amazonS3.getObject(bucketName, s3ObjectKey);
        InputStream objectContent = s3Object.getObjectContent();
        List<ProductDto> products = objectMapper
                .readValue(objectContent, new TypeReference<List<ProductDto>>() {});
        for (ProductDto product : products) {
           log.info("Received product from SQS message: " + product);
        }
        // Delete the SQS message and S3 object
        amazonS3.deleteObject(bucketName, s3ObjectKey);
        System.out.println("Deleted file from S3 bucket: " + bucketName + ", key: " + s3ObjectKey);


    }



}
