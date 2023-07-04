package com.myapps.rds.service;



import com.myapps.rds.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    List<Product> getAll();

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void deleteAllProducts();
}
