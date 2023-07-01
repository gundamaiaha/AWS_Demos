package com.myapps.productspublisher.publisher;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapps.productspublisher.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductsPublisherService {

    private final AmazonSQS sqs;
    private final AmazonS3 s3Client;
    private final ObjectMapper mapper;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.sqs.queue}")
    private String sqsQueue;

    public void publishProductsData(final List<ProductDto> products) throws IOException {

        final String productsJson = mapper.writeValueAsString(products);
        final String s3ObjectKey = "products" + LocalDate.now().toString() + ".json";
        // File file= new File("products.json");
//        mapper.writerWithDefaultPrettyPrinter()
//                .writeValue(file,products);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(productsJson.getBytes());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(productsJson.getBytes().length);

        PutObjectRequest request = new PutObjectRequest(bucketName, s3ObjectKey, inputStream, metadata);
        s3Client.putObject(request);

        log.info("uploaded the products json to s3");

        sqs.sendMessage(sqsQueue, s3ObjectKey);

        System.out.println("Sent message to SQS queue: " + sqsQueue + " with message body: " + s3ObjectKey);


    }


}
