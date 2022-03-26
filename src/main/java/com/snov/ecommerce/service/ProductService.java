package com.snov.ecommerce.service;

import com.snov.ecommerce.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    String addProduct(Product product);

    List<Product> searchProducts(Map<String, Object> payload);
}
