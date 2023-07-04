package com.myapps.rds.service;


import com.myapps.rds.exception.ProductNotFoundException;
import com.myapps.rds.model.Product;
import com.myapps.rds.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = getProductById(productId);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return existingProduct;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        Product existingProduct = getProductById(id);
        productRepository.delete(existingProduct);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
