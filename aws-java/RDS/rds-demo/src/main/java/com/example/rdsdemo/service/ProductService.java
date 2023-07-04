package com.example.rdsdemo.service;

import com.example.rdsdemo.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    List<Product> getAll();

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void deleteAllProducts();
}
