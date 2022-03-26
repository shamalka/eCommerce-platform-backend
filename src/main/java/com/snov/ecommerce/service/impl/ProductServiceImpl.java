package com.snov.ecommerce.service.impl;

import com.snov.ecommerce.entity.Product;
import com.snov.ecommerce.repository.ProductRepository;
import com.snov.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addProduct(Product product) {
        productRepository.addProduct(product);
        return "Done";
    }

    @Override
    public List<Product> searchProducts(Map<String, Object> payload) {
        return productRepository.searchProducts(payload);
    }
}
