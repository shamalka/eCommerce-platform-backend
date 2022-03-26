package com.snov.ecommerce.repository;

import com.snov.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "INSERT INTO category (name, description, thumbnail) VALUES (?1, ?2, ?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void addCategory(String name, String description, String thumbnail);

}
