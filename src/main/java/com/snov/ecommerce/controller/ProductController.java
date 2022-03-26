package com.snov.ecommerce.controller;

import com.snov.ecommerce.entity.Product;
import com.snov.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody Map<String, Object> payload) {
        return productService.searchProducts(payload);
    }
}
