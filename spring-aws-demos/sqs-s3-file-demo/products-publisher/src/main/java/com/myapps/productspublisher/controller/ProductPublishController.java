package com.myapps.productspublisher.controller;


import com.myapps.productspublisher.dto.ProductDto;
import com.myapps.productspublisher.publisher.ProductsPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publish")
public class ProductPublishController {

    private final ProductsPublisherService productsPublisherService;


    @PostMapping("/products")
    public ResponseEntity<String> uploadProductsData(@RequestBody List<ProductDto> products) throws IOException {
        productsPublisherService.publishProductsData(products);
        return ResponseEntity.ok("Products data published successfully.");
    }
}
