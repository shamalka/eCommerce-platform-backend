package com.snov.ecommerce.repository.impl;

import com.snov.ecommerce.entity.Product;
import com.snov.ecommerce.repository.CustomProductRepository;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void addProduct(Product product) {
        Session session = entityManager.unwrap(Session.class).getSession();
        Query insertQuery = session.createNativeQuery("INSERT INTO product(sku, name, price, weight, description, thumbnail, image, category, created_date, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        insertQuery.setParameter(1, product.getSku());
        insertQuery.setParameter(2, product.getName());
        insertQuery.setParameter(3, product.getPrice());
        insertQuery.setParameter(4, product.getWeight());
        insertQuery.setParameter(5, product.getDescription());
        insertQuery.setParameter(6, product.getThumbnail());
        insertQuery.setParameter(7, product.getImage());
        insertQuery.setParameter(8, product.getCategory());
        insertQuery.setParameter(9, product.getCreatedDate());
        insertQuery.setParameter(10, product.getStock());
        insertQuery.executeUpdate();

        BigInteger lastInsertId = (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();

        Query lifecycleQuery = session.createNativeQuery("INSERT INTO product_has_category(product_id, category_id) VALUES (?1, ?2)");
        lifecycleQuery.setParameter(1, lastInsertId);
        lifecycleQuery.setParameter(2, product.getCategory());
        lifecycleQuery.executeUpdate();

        session.close();
    }

    @Override
    public List<Product> searchProducts(Map<String, Object> payload) {
        int parameterIndex = 1;
        String query = "SELECT * " +
                "FROM product WHERE category = ?" + parameterIndex;
        if (payload.get("sku") != null) {
            query = query + " AND sku LIKE ?" + ++parameterIndex;
            payload.replace("sku", "%" + payload.get("sku") + "%");
        }
        if (payload.get("name") != null) {
            query = query + " AND name LIKE ?" + ++parameterIndex;
            payload.replace("name", "%" + payload.get("name") + "%");
        }
        if (payload.get("min_price") != null && payload.get("max_price") != null) {
            query = query + " AND price BETWEEN ?" + ++parameterIndex + " AND ?" + ++parameterIndex;
        }
        if (payload.get("min_weight") != null && payload.get("max_weight") != null) {
            query = query + " AND weight BETWEEN ?" + ++parameterIndex + " AND ?" + ++parameterIndex;
        }
        if (payload.get("description") != null) {
            query = query + " AND description LIKE ?" + ++parameterIndex;
            payload.replace("description", "%" + payload.get("description") + "%");
        }
        if (payload.get("from_date") != null && payload.get("to_date") != null) {
            query = query + " AND weight BETWEEN ?" + ++parameterIndex + " AND ?" + ++parameterIndex;
        }
        query = query + " LIMIT ?" + ++parameterIndex + " OFFSET ?" + ++parameterIndex;
        Query nativeQuery = entityManager.createNativeQuery(query, Product.class);
        AtomicInteger queryParamIndex = new AtomicInteger(0);
        payload.forEach((k, v) -> {
            if (v != null) {
                nativeQuery.setParameter(queryParamIndex.incrementAndGet(), v);
            }
        });
        return nativeQuery.getResultList();
    }
}
