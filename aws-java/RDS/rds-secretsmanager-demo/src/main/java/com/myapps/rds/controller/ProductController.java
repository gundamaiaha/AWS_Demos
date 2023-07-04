package com.myapps.rds.controller;


import com.myapps.rds.model.Product;
import com.myapps.rds.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }


    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }


    @DeleteMapping("/{productId}")
    public String deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return "Product with id :"+productId+ " was deleted successfully!";
    }

    @DeleteMapping
    public String deleteAllProduct(){
        productService.deleteAllProducts();
        return "All products deleted successfully";
    }


}
