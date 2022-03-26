package com.snov.ecommerce.repository;

import com.snov.ecommerce.entity.Product;

import java.util.List;
import java.util.Map;

public interface CustomProductRepository {

    void addProduct(Product product);

    List<Product> searchProducts(Map<String, Object> payload);
}
