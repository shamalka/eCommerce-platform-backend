package com.snov.ecommerce.repository;

import com.snov.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, CustomProductRepository {

}
